package org.excel.toData.bean;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml对应excel 列具体校验
 * 
 * @author lch
 * @version 1.0
 */
@XmlRootElement(name = "rule")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRule {
	private String function;
	@XmlAttribute(name = "default")
	private String defaultStr;
	private String message;
	private Map<String, String> item;

	/**
	 * xml中rule节点下的校验方法
	 * 
	 * @return String类型
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * 写入xml中rule节点下的校验方法
	 * 
	 * @param function
	 *            全路径的方法名
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * 获取xml中rule节点下默认值
	 * 
	 * @return String类型
	 */
	public String getDefaultStr() {
		return defaultStr;
	}

	/**
	 * 
	 * @param defaultStr
	 *            默认字符
	 */
	public void setDefaultStr(String defaultStr) {
		this.defaultStr = defaultStr;
	}

	/**
	 * 获取校验的提示信息
	 * 
	 * @return String类型
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置校验信息
	 * 
	 * @param message 设置校验信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取xml中rule节点下map信息
	 * 
	 * @return Map类型
	 */
	public Map<String, String> getItem() {
		return item;
	}

	/**
	 * 设置xml中rule节点下map信息
	 * 
	 * @param item
	 *            Map类型
	 */
	public void setItem(Map<String, String> item) {
		this.item = item;
	}

}
