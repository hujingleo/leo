package com.youxin.ymall.exceptions;

public class TianhuiException extends RuntimeException {

	private static final long serialVersionUID = -7041184474047630509L;
	private Integer errorcode;
	private String message;

	public TianhuiException(Integer errorcode, String message) {
		super();
		this.errorcode = errorcode;
		this.message = message;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
