package com.youxin.ymanage.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//每日运营报表实体类
public class DailyOperationReport {
	// 客户名称
	private String factoryname;
	// 地址
	private String factory_address;
	// 区域
	private String factory_buluoname;
	// 镇区
	private String factory_culuoname;
	// 房间数
	private int factory_roomnum;
	// 核算使用人数
	public int factory_fugairs;
	// 开通日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date factory_opentime;
	// 统计id
	private int sta_wifiuser_oid;
	// 统计日期
	private Date sta_wifiuser_stadate;
	// 工厂id
	private int sta_wifi_user_factoryid;
	// 注册数
	private int sta_wifiuser_regcount;
	// 环比昨日
	private int sta_wifiuser_regcountchg1;
	// 缴费数
	private int sta_wifiuser_wifipaycount;
	// 环比昨日
	private int sta_wifiuser_wifipaycountchg1;
	// 环比上月
	private int sta_wifiuser_wifipaycountchg2;
	// 环比上周
	private int sta_wifiuser_wifipaycountchg3;
	// 使用率=缴费数/核算使用人数
	private float sta_wifiuser_wifipaycountusage;
	// 使用率环比昨日
	private float sta_wifiuser_wifipaycountusagechg1;
	// 覆盖率
	private float sta_wifiuser_eachroomcountusage;
	// 上月覆盖率环比
	private float sta_wifiuser_eachroomcountusage_mom;
	// 上周覆盖率环比
	private float sta_wifiuser_eachroomcountusage_wow;
	// 运营类别
	private int operating_level;
	// 运营等级
	private String operatlevel;

	public String getOperatlevel() {
		return operatlevel;
	}

	public void setOperatlevel(String operatlevel) {
		this.operatlevel = operatlevel;
	}

	// 带宽数量
	private int bandwidth_num;

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getFactory_address() {
		return factory_address;
	}

	public void setFactory_address(String factory_address) {
		this.factory_address = factory_address;
	}

	public String getFactory_buluoname() {
		return factory_buluoname;
	}

	public void setFactory_buluoname(String factory_buluoname) {
		this.factory_buluoname = factory_buluoname;
	}

	public String getFactory_culuoname() {
		return factory_culuoname;
	}

	public void setFactory_culuoname(String factory_culuoname) {
		this.factory_culuoname = factory_culuoname;
	}

	public int getFactory_roomnum() {
		return factory_roomnum;
	}

	public void setFactory_roomnum(Integer factory_roomnum) {
		this.factory_roomnum = factory_roomnum;
	}

	public int getFactory_fugairs() {
		return factory_fugairs;
	}

	public void setFactory_fugairs(int factory_fugairs) {
		this.factory_fugairs = factory_fugairs;
	}

	public Date getFactory_opentime() {
		return factory_opentime;
	}

	public void setFactory_opentime(Date factory_opentime) {
		this.factory_opentime = factory_opentime;
	}

	public int getSta_wifiuser_regcount() {
		return sta_wifiuser_regcount;
	}

	public void setSta_wifiuser_regcount(int sta_wifiuser_regcount) {
		this.sta_wifiuser_regcount = sta_wifiuser_regcount;
	}

	public int getSta_wifiuser_regcountchg1() {
		return sta_wifiuser_regcountchg1;
	}

	public void setSta_wifiuser_regcountchg1(int sta_wifiuser_regcountchg1) {
		this.sta_wifiuser_regcountchg1 = sta_wifiuser_regcountchg1;
	}

	public int getSta_wifiuser_wifipaycountchg2() {
		return sta_wifiuser_wifipaycountchg2;
	}

	public void setSta_wifiuser_wifipaycountchg2(int sta_wifiuser_wifipaycountchg2) {
		this.sta_wifiuser_wifipaycountchg2 = sta_wifiuser_wifipaycountchg2;
	}

	public int getSta_wifiuser_wifipaycountchg3() {
		return sta_wifiuser_wifipaycountchg3;
	}

	public void setSta_wifiuser_wifipaycountchg3(int sta_wifiuser_wifipaycountchg3) {
		this.sta_wifiuser_wifipaycountchg3 = sta_wifiuser_wifipaycountchg3;
	}

	public int getSta_wifiuser_wifipaycount() {
		return sta_wifiuser_wifipaycount;
	}

	public void setSta_wifiuser_wifipaycount(int sta_wifiuser_wifipaycount) {
		this.sta_wifiuser_wifipaycount = sta_wifiuser_wifipaycount;
	}

	public int getSta_wifiuser_wifipaycountchg1() {
		return sta_wifiuser_wifipaycountchg1;
	}

	public void setSta_wifiuser_wifipaycountchg1(int sta_wifiuser_wifipaycountchg1) {
		this.sta_wifiuser_wifipaycountchg1 = sta_wifiuser_wifipaycountchg1;
	}

	public float getSta_wifiuser_wifipaycountusage() {
		return sta_wifiuser_wifipaycountusage;
	}

	public void setSta_wifiuser_wifipaycountusage(float sta_wifiuser_wifipaycountusage) {
		this.sta_wifiuser_wifipaycountusage = sta_wifiuser_wifipaycountusage;
	}

	public int getOperating_level() {
		return operating_level;
	}

	public void setOperating_level(int operating_level) {
		this.operating_level = operating_level;
	}

	public int getBandwidth_num() {
		return bandwidth_num;
	}

	public void setBandwidth_num(int bandwidth_num) {
		this.bandwidth_num = bandwidth_num;
	}

	public int getSta_wifiuser_oid() {
		return sta_wifiuser_oid;
	}

	public void setSta_wifiuser_oid(int sta_wifiuser_oid) {
		this.sta_wifiuser_oid = sta_wifiuser_oid;
	}

	public Date getSta_wifiuser_stadate() {
		return sta_wifiuser_stadate;
	}

	public void setSta_wifiuser_stadate(Date sta_wifiuser_stadate) {
		this.sta_wifiuser_stadate = sta_wifiuser_stadate;
	}

	public int getSta_wifi_user_factoryid() {
		return sta_wifi_user_factoryid;
	}

	public void setSta_wifi_user_factoryid(int sta_wifi_user_factoryid) {
		this.sta_wifi_user_factoryid = sta_wifi_user_factoryid;
	}

	public float getSta_wifiuser_wifipaycountusagechg1() {
		return sta_wifiuser_wifipaycountusagechg1;
	}

	public void setSta_wifiuser_wifipaycountusagechg1(float sta_wifiuser_wifipaycountusagechg1) {
		this.sta_wifiuser_wifipaycountusagechg1 = sta_wifiuser_wifipaycountusagechg1;
	}

	public float getSta_wifiuser_eachroomcountusage() {
		return sta_wifiuser_eachroomcountusage;
	}

	public void setSta_wifiuser_eachroomcountusage(float sta_wifiuser_eachroomcountusage) {
		this.sta_wifiuser_eachroomcountusage = sta_wifiuser_eachroomcountusage;
	}

	public float getSta_wifiuser_eachroomcountusage_mom() {
		return sta_wifiuser_eachroomcountusage_mom;
	}

	public void setSta_wifiuser_eachroomcountusage_mom(float sta_wifiuser_eachroomcountusage_mom) {
		this.sta_wifiuser_eachroomcountusage_mom = sta_wifiuser_eachroomcountusage_mom;
	}

	public float getSta_wifiuser_eachroomcountusage_wow() {
		return sta_wifiuser_eachroomcountusage_wow;
	}

	public void setSta_wifiuser_eachroomcountusage_wow(float sta_wifiuser_eachroomcountusage_wow) {
		this.sta_wifiuser_eachroomcountusage_wow = sta_wifiuser_eachroomcountusage_wow;
	}
}
