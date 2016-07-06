package org.excel.toData.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml对应一种excel
 * 
 * @author lch
 * @version 1.0
 *
 */
@XmlRootElement(name = "XmlData")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlData {
	private List<XmlCell> listXmlCell;

	/**
	 * 获取对应excel的多个列
	 * 
	 * @return list类型
	 */
	public List<XmlCell> getListXmlCell() {
		return listXmlCell;
	}

	/**
	 * 写入对应excel的多个列
	 * 
	 * @param listXmlCell excel的多个列
	 */
	public void setListXmlCell(List<XmlCell> listXmlCell) {
		this.listXmlCell = listXmlCell;
	}

}
