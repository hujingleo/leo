package com.youxin.ymall.exceptions;

public class LuckDrawException extends RuntimeException {

	private static final long serialVersionUID = 2247025856227960948L;

	public LuckDrawException(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	private Integer code;
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
