package com.youxin.ymanage.domain;

/**
 * 数据列
 * 
 * @author yukun
 *
 */
public class DataColumn {

	private String fieldname;
	private String fieldlabel;

	/**
	 * number,string,date
	 */
	private String fieldtype;
	private String pattern;

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldlabel() {
		return fieldlabel;
	}

	public void setFieldlabel(String fieldlabel) {
		this.fieldlabel = fieldlabel;
	}

	public String getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}

}
