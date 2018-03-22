package com.youxin.ymanage.entity;

import java.io.Serializable;

/**
 * Description:用于螺丝钉统计工厂的用户信息，每天统计昨天的，每个工厂每天一条记录
 * 
 * @author ljw 2016-3-5
 * @version v1.0
 */
public class FactoryStaInfo implements Serializable {
	private static final long serialVersionUID = -9191798664075206703L;
	private Integer sta_factory_info_oid;// 自增id
	private Integer sta_factory_info_factoryid;// 工厂id
	private String sta_factory_info_factoryname;// 工厂名字
	private Integer sta_factory_info_zfbusernum;// 支付宝的用户，查询工厂的用户，然后查看订单表
	private Integer sta_factory_info_registerneverpaywifinum;// 注册从没缴费wifi用户数，查看订单是否有记录
	private Integer sta_factory_info_wifipayexpirenum;// wifi到期未缴费数量，判断用户的时间到期了没有
	private Integer sta_factory_info_registerneverpaytvnum;// 注册从没缴费tv用户数,查看有没有订单
	private Integer sta_factory_info_wxusernum;// 微信用户数
	private Integer sta_factory_info_tvpayexpirenum;// wifi到期未缴费数量
	private Integer sta_factory_info_mianfeiwifinopaynum;// 免费上网未缴费用户
	private Integer sta_factory_info_yesterdaylogiwifinnopaynum;// 昨日登录未缴费用户

	public Integer getSta_factory_info_oid() {
		return sta_factory_info_oid;
	}

	public void setSta_factory_info_oid(Integer sta_factory_info_oid) {
		this.sta_factory_info_oid = sta_factory_info_oid;
	}

	public Integer getSta_factory_info_factoryid() {
		return sta_factory_info_factoryid;
	}

	public void setSta_factory_info_factoryid(Integer sta_factory_info_factoryid) {
		this.sta_factory_info_factoryid = sta_factory_info_factoryid;
	}

	public String getSta_factory_info_factoryname() {
		return sta_factory_info_factoryname;
	}

	public void setSta_factory_info_factoryname(String sta_factory_info_factoryname) {
		this.sta_factory_info_factoryname = sta_factory_info_factoryname;
	}

	public Integer getSta_factory_info_zfbusernum() {
		return sta_factory_info_zfbusernum;
	}

	public void setSta_factory_info_zfbusernum(Integer sta_factory_info_zfbusernum) {
		this.sta_factory_info_zfbusernum = sta_factory_info_zfbusernum;
	}

	public Integer getSta_factory_info_registerneverpaywifinum() {
		return sta_factory_info_registerneverpaywifinum;
	}

	public void setSta_factory_info_registerneverpaywifinum(Integer sta_factory_info_registerneverpaywifinum) {
		this.sta_factory_info_registerneverpaywifinum = sta_factory_info_registerneverpaywifinum;
	}

	public Integer getSta_factory_info_wifipayexpirenum() {
		return sta_factory_info_wifipayexpirenum;
	}

	public void setSta_factory_info_wifipayexpirenum(Integer sta_factory_info_wifipayexpirenum) {
		this.sta_factory_info_wifipayexpirenum = sta_factory_info_wifipayexpirenum;
	}

	public Integer getSta_factory_info_registerneverpaytvnum() {
		return sta_factory_info_registerneverpaytvnum;
	}

	public void setSta_factory_info_registerneverpaytvnum(Integer sta_factory_info_registerneverpaytvnum) {
		this.sta_factory_info_registerneverpaytvnum = sta_factory_info_registerneverpaytvnum;
	}

	public Integer getSta_factory_info_wxusernum() {
		return sta_factory_info_wxusernum;
	}

	public void setSta_factory_info_wxusernum(Integer sta_factory_info_wxusernum) {
		this.sta_factory_info_wxusernum = sta_factory_info_wxusernum;
	}

	public Integer getSta_factory_info_tvpayexpirenum() {
		return sta_factory_info_tvpayexpirenum;
	}

	public void setSta_factory_info_tvpayexpirenum(Integer sta_factory_info_tvpayexpirenum) {
		this.sta_factory_info_tvpayexpirenum = sta_factory_info_tvpayexpirenum;
	}

	public Integer getSta_factory_info_mianfeiwifinopaynum() {
		return sta_factory_info_mianfeiwifinopaynum;
	}

	public void setSta_factory_info_mianfeiwifinopaynum(Integer sta_factory_info_mianfeiwifinopaynum) {
		this.sta_factory_info_mianfeiwifinopaynum = sta_factory_info_mianfeiwifinopaynum;
	}

	public Integer getSta_factory_info_yesterdaylogiwifinnopaynum() {
		return sta_factory_info_yesterdaylogiwifinnopaynum;
	}

	public void setSta_factory_info_yesterdaylogiwifinnopaynum(Integer sta_factory_info_yesterdaylogiwifinnopaynum) {
		this.sta_factory_info_yesterdaylogiwifinnopaynum = sta_factory_info_yesterdaylogiwifinnopaynum;
	}

}
