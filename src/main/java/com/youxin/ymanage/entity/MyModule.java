package com.youxin.ymanage.entity;

/**
 * 我的功能模块实体
 * 
 * @author Administrator
 * 
 */
public class MyModule {

	private Integer oid; // 自增长id
	private String moduleName; // 名称
	private String oclass; // 功能块的类型
	private Integer stateMent; // 是否为原生(1/0 是/否)
	private String androidUrl;// 安卓URL
	private String iosUrl; // 苹果URL
	private String imgageUrl; // 图片地址
	private String create_time;// 创建/更新日期
	private Integer orderid; // 排序编号

	public MyModule() {
	}

	public MyModule(String moduleName, String oclass, Integer stateMent, String androidUrl, String iosUrl,
			String imgageUrl, String create_time) {
		super();
		this.moduleName = moduleName;
		this.oclass = oclass;
		this.stateMent = stateMent;
		this.androidUrl = androidUrl;
		this.iosUrl = iosUrl;
		this.imgageUrl = imgageUrl;
		this.create_time = create_time;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOclass() {
		return oclass;
	}

	public void setOclass(String oclass) {
		this.oclass = oclass;
	}

	public Integer getStateMent() {
		return stateMent;
	}

	public void setStateMent(Integer stateMent) {
		this.stateMent = stateMent;
	}

	public String getAndroidUrl() {
		return androidUrl;
	}

	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}

	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}

	public String getImgageUrl() {
		return imgageUrl;
	}

	public void setImgageUrl(String imgageUrl) {
		this.imgageUrl = imgageUrl;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

}
