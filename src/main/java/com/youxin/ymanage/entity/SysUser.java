package com.youxin.ymanage.entity;

public class SysUser {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String username;

	public String getUsername() {
		return this.username;

	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password;

	public String getPassword() {
		return this.password;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String truename;

	public String getTruename() {
		return this.truename;

	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	private int oid;

	public int getOid() {
		return this.oid;

	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	private Boolean islocked;

	public Boolean getIslocked() {
		return this.islocked;

	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	private Boolean isenabled;

	public Boolean getIsenabled() {
		return this.isenabled;

	}

	public void setIsenabled(Boolean isenabled) {
		this.isenabled = isenabled;
	}

	private Boolean iscredentialsnonexpired;

	public Boolean getIscredentialsnonexpired() {
		return this.iscredentialsnonexpired;

	}

	public void setIscredentialsnonexpired(Boolean iscredentialsnonexpired) {
		this.iscredentialsnonexpired = iscredentialsnonexpired;
	}

	private Boolean isaccountnonexpired;

	public Boolean getIsaccountnonexpired() {
		return this.isaccountnonexpired;

	}

	public void setIsaccountnonexpired(Boolean isaccountnonexpired) {
		this.isaccountnonexpired = isaccountnonexpired;
	}

	private int depid;

	public int getDepid() {
		return this.depid;

	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	private String email;

	public String getEmail() {
		return this.email;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	private int orderid;

	public int getOrderid() {
		return this.orderid;

	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	private String rolecode;

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	private String loginurl;

	public String getLoginurl() {
		return loginurl;
	}

	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}

	private Integer factoryid;// 用户所在工厂id

	public Integer getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(Integer factoryid) {
		this.factoryid = factoryid;
	}

	private String factoryname;// 工厂名字

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", password=" + password + ", truename=" + truename
				+ ", oid=" + oid + ", islocked=" + islocked + ", isenabled=" + isenabled + ", iscredentialsnonexpired="
				+ iscredentialsnonexpired + ", isaccountnonexpired=" + isaccountnonexpired + ", depid=" + depid
				+ ", email=" + email + ", orderid=" + orderid + ", rolecode=" + rolecode + ", rolename=" + rolename
				+ ", loginurl=" + loginurl + ", factoryid=" + factoryid + ", factoryname=" + factoryname + "]";
	}

}