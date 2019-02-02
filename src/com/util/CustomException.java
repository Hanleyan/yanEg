package com.util;

/**
 * @author Hanley 2019年1月28日下午5:52:08
 *
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8577116005026566153L;
	// 异常信息
	private String message;

	public CustomException(String message) {
		super(message);
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
