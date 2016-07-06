package org.excel.toData.code;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.excel.toData.bean.ErrorMessage;
import org.excel.toData.bean.XmlCell;
import org.excel.toData.bean.XmlData;
import org.excel.toData.bean.XmlRule;
/**
 * 核心代码
 * @author lch
 * @version 1.0
 */
public class Operation {

	/**
	 * 上传的表格对应xml，并返回表格列数字
	 * 
	 * @param customizer excel对应的xml转成的对象
	 * @param row excel行信息
	 * @return 返回表格列对应数字和xml
	 */
	public Map<String, XmlCell> getCellByName(XmlData customizer, Row row) {
		Map<String, XmlCell> exceL = new HashMap<>();
		for (Cell cell : row) {
			// 校验类循环对比列行，把匹配的list写入map
			// 读取的时候直接根据list 第几个插入数据
			for (int i = 0; i < customizer.getListXmlCell().size(); i++) {
				// 匹配对应xml配置文件
				if (customizer.getListXmlCell().get(i).getName().equals(cell.toString())) {
					exceL.put(String.valueOf(cell.getColumnIndex()), customizer.getListXmlCell().get(i));
				}
			}
			System.out.println(cell.getColumnIndex() + ";" + cell.toString());
		}
		return exceL;
	}

	/**
	 * 根据sheet返回数据list
	 * 
	 * @param file xml文件路径
	 * @param sheet excel的sheet
	 * @param  excel 要生成的数据类型，类的全路径
	 * @return list,list里面有map,map包含excel根据xml转换的数据、是否有错误信息和校验的信息
	 * @throws Exception
	 *             中包括JAXB、ClassNotFound和NoSuchMethod等
	 */
	public List<Object> getListObject(File file, Sheet sheet, String excel) throws Exception {
		// xml文件
		JAXBContext jaxb = JAXBContext.newInstance(XmlData.class);
		Unmarshaller unm = jaxb.createUnmarshaller();
		XmlData customizer = (XmlData) unm.unmarshal(file);

		List<Object> listExcelData = new ArrayList<>();
		Map<String, XmlCell> exceL = new HashMap<>();

		boolean checkError = true;
		Object excelObject = null;
		Object ziy = Class.forName(excel);
		// 利用foreach循环 遍历sheet中的所有行
		for (Row row : sheet) {
			excelObject = ((Class<?>) ziy).newInstance(); // 自定义pojo与数据库交互对象
			Map<String, Object> excelData = new HashMap<>(); // 返回list数据map
			if (row.getRowNum() == 0) {
				exceL = getCellByName(customizer, row);
			} else {
				List<ErrorMessage> errorList = new ArrayList<>();
				// 1.循环所有列,根据每个列的数字取对应xml对象
				int lastColumn = Math.max(row.getLastCellNum(), row.getFirstCellNum());
				String cellStr = "";
				for (int i = 0; i < lastColumn; i++) {
					// Map<String, String> mapObject = new HashMap<>();
					Cell cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
					XmlCell xmlCell = null;
					if (cell == null) {
						cell = row.createCell(i);
						xmlCell = exceL.get(String.valueOf(i));
					} else {
						xmlCell = exceL.get(String.valueOf(cell.getColumnIndex()));
					}
					cellStr = cell.toString();
					// 2.校验
					if (xmlCell.getXmlRules() != null && xmlCell.getXmlRules().getXmlRule() != null) {

						for (XmlRule xmlRule : xmlCell.getXmlRules().getXmlRule()) {
							Class<?> classType = Class.forName(
									xmlRule.getFunction().substring(0, xmlRule.getFunction().lastIndexOf(".")));
							String mot = xmlRule.getFunction().substring(xmlRule.getFunction().lastIndexOf(".") + 1,
									xmlRule.getFunction().length());

							Class<?>[] parameter = { String.class, String.class, String.class };
							Object[] argument = { xmlCell.getName(), cellStr, xmlRule.getMessage() };

							if (xmlRule.getItem() != null) {
								parameter = new Class[] { String.class, Map.class, String.class, String.class };
								argument = new Object[] { xmlCell.getName(), xmlRule.getItem(), cellStr,
										xmlRule.getMessage() };
							}

							// 这里假设你的参数只有一个String类型的
							Method method = classType.getDeclaredMethod(mot, parameter);

							// 这个object就是你调用方法返回的对象
							Object object = method.invoke(classType.newInstance(), argument);
							if (object instanceof ErrorMessage) {
								ErrorMessage message = (ErrorMessage) object;
								if (!message.getMessage().equals("true")) {
									checkError = false;
								}
								errorList.add(message);
							} else if (object instanceof String) {
								cellStr = object.toString();
							}
						}
					}
					// 获得的数据返回map
					Class<?> myexcel = (Class<?>) ziy;
					myexcel.getMethod("set" + xmlCell.getCode(), String.class).invoke(excelObject, cellStr);
				}
				excelData.put("errorMessageList", errorList);
				excelData.put("excelObject", excelObject);
				excelData.put("checkError", checkError);

				listExcelData.add(excelData);
			}
		}
		return listExcelData;

	}
}
