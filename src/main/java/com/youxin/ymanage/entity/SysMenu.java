package com.youxin.ymanage.entity;

import java.util.ArrayList;
import java.util.List;

public class SysMenu {

	private String ocode;

	public String getOcode() {
		return this.ocode;

	}

	public void setOcode(String ocode) {
		this.ocode = ocode;
	}

	private String oname;

	public String getOname() {
		return this.oname;

	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	private int orderid;

	public int getOrderid() {
		return this.orderid;

	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	private String parentcode;

	public String getParentcode() {
		return this.parentcode;

	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	private String url;

	public String getUrl() {
		return this.url;

	}

	public void setUrl(String url) {
		this.url = url;
	}

	private List<SysMenu> childmenus = new ArrayList<SysMenu>();

	public List<SysMenu> getChildmenus() {
		return childmenus;
	}

	public void setChildmenus(List<SysMenu> childmenus) {
		this.childmenus = childmenus;
	}

	private String modulecode;

	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	private String oclass;
	private String permissionurl;

	public String getOclass() {
		return oclass;
	}

	public void setOclass(String oclass) {
		this.oclass = oclass;
	}

	public String getPermissionurl() {
		return permissionurl;
	}

	public void setPermissionurl(String permissionurl) {
		this.permissionurl = permissionurl;
	}

	private int ostatus;

	public int getOstatus() {
		return ostatus;
	}

	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}

	@Override
	public String toString() {
		return "SysMenu [ocode=" + ocode + ", oname=" + oname + ", orderid=" + orderid + ", parentcode=" + parentcode
				+ ", url=" + url + ", childmenus=" + childmenus + ", modulecode=" + modulecode + ", oclass=" + oclass
				+ ", permissionurl=" + permissionurl + ", ostatus=" + ostatus + "]";
	}

}