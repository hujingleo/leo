package com.youxin.ymall.exceptions;

/**
 * 执行实际数据库业务操作出现异常
 * 
 * @author yukun
 *
 */
public class OperDBException extends RuntimeException {

	public OperDBException() {
	}

	public OperDBException(String msg) {

		super(msg);

	}
}
