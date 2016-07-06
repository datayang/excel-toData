package org.excel.toData.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * xml对应excel 列的各个校验
 * 
 * @author lch
 * @version 1.0
 */
@XmlRootElement(name = "XmlRules")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRules {
	@XmlElement(name = "XmlRule")
	private List<XmlRule> xmlRule;

	/**
	 * 获取xml中XmlRules节点下的校验
	 * 
	 * @return List类型
	 */
	public List<XmlRule> getXmlRule() {
		return xmlRule;
	}

	/**
	 * 写入xml中XmlRules节点下的校验
	 * 
	 * @param xmlRule List类型 
	 */
	public void setXmlRule(List<XmlRule> xmlRule) {
		this.xmlRule = xmlRule;
	}

}
