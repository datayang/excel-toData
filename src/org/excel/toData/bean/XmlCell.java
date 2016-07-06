package org.excel.toData.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml对应excel 列
 * 
 * @author lch
 * @version 1.0
 *
 */
@XmlRootElement(name = "XmlCell")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCell {
	private String name;
	private String code;
	@XmlElement(name = "XmlRules")
	private XmlRules xmlRules;

	/**
	 * 获取与excel对应的名称
	 * 
	 * @return 返回String类型
	 */
	public String getName() {
		return name;
	}

	/**
	 * 写入与excel名称
	 * 
	 * @param name excel列名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取与excel对应的名称的属性
	 * 
	 * @return 返回String类型数据
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 写入与excel对应的名称的属性
	 * @param code 自定义表格类的属性
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取一个excel列多个校验
	 * 
	 * @return 返回xmlRules类型数据
	 */
	public XmlRules getXmlRules() {
		return xmlRules;
	}

	/**
	 * 写入一个excel列多个校验
	 * 
	 * @param xmlRules xmlRules对象
	 */
	public void setXmlRules(XmlRules xmlRules) {
		this.xmlRules = xmlRules;
	}

}
