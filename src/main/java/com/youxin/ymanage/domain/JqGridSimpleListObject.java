package com.youxin.ymanage.domain;

import com.rsclouds.base.SimpleListObject;

public class JqGridSimpleListObject<T> extends SimpleListObject<T> {

	private Object userdata;

	public Object getUserdata() {
		return userdata;
	}

	public void setUserdata(Object userdata) {
		this.userdata = userdata;
	}

}
