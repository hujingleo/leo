package com.youxin.ymall.entity;

import org.springframework.format.annotation.DateTimeFormat;

public class Buslog {

	private int oid;

	public int getOid() {
		return this.oid;

	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	private String otype;

	public String getOtype() {
		return this.otype;

	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	private String msg;

	public String getMsg() {
		return this.msg;

	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date odate;

	public java.util.Date getOdate() {
		return this.odate;

	}

	public void setOdate(java.util.Date odate) {
		this.odate = odate;
	}

	private String operator;

	public String getOperator() {
		return this.operator;

	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	private String oclass;

	public String getOclass() {
		return this.oclass;

	}

	public void setOclass(String oclass) {
		this.oclass = oclass;
	}

	private String module;

	public String getModule() {
		return this.module;

	}

	public void setModule(String module) {
		this.module = module;
	}

	private String ofunc;

	public String getOfunc() {
		return this.ofunc;

	}

	public void setOfunc(String ofunc) {
		this.ofunc = ofunc;
	}

}