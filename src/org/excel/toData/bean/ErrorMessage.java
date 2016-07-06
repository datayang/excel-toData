package org.excel.toData.bean;

/**
 * 错误信息
 * 
 * @author lch
 * @version 1.0
 *
 */
public class ErrorMessage {
	private String name;// 列名
	private String message;// 验证的错误信息

	/**
	 * 获取excel列名
	 * 
	 * @return 返回String类型
	 */
	public String getName() {
		return name;
	}

	/**
	 * 写入列名
	 * 
	 * @param name 列名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取消息
	 * 
	 * @return 返回String 类型
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 写入消息
	 * 
	 * @param message 写入消息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
