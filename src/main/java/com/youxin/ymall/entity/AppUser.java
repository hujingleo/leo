package com.youxin.ymall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rsclouds.util.StringTool;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppUser implements Serializable {

	public AppUser() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6765073246260051916L;
	// private int id;
	//
	// public int getId() {
	// return this.id;
	//
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }

	private int version;

	public int getVersion() {
		return this.version;

	}

	public void setVersion(int version) {
		this.version = version;
	}

	private int location_id;

	public int getLocation_id() {
		return this.location_id;

	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
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

	private String name;

	public String getName() {
		return this.name;

	}

	public void setName(String name) {
		this.name = name;
	}

	private int points;

	public int getPoints() {
		return this.points;

	}

	public void setPoints(int points) {
		this.points = points;
	}

	private int points_converted;

	public int getPoints_converted() {
		return this.points_converted;

	}

	public void setPoints_converted(int points_converted) {
		this.points_converted = points_converted;
	}

	private int points_earned;

	public int getPoints_earned() {
		return this.points_earned;

	}

	public void setPoints_earned(int points_earned) {
		this.points_earned = points_earned;
	}

	private String status;

	public String getStatus() {
		return this.status;

	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String salt;

	public String getSalt() {
		return this.salt;

	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	private String gender;

	public String getGender() {
		return this.gender;

	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private String email;

	public String getEmail() {
		return this.email;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	private Date modified_datetime;

	public Date getModified_datetime() {
		return this.modified_datetime;

	}

	public void setModified_datetime(Date modified_datetime) {
		this.modified_datetime = modified_datetime;
	}

	private Date created_datetime;

	public Date getCreated_datetime() {
		return this.created_datetime;

	}

	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}

	private Date last_login;

	public Date getLast_login() {
		return this.last_login;

	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	private String help_recharge;

	public String getHelp_recharge() {
		return this.help_recharge;

	}

	public void setHelp_recharge(String help_recharge) {
		this.help_recharge = help_recharge;
	}

	private String bind_mobile;

	public String getBind_mobile() {
		return this.bind_mobile;

	}

	public void setBind_mobile(String bind_mobile) {
		this.bind_mobile = bind_mobile;
	}

	private String mobile;

	public String getMobile() {
		return this.mobile;

	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String template_name;

	public String getTemplate_name() {
		return this.template_name;

	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date memshipdate;

	public Date getMemshipdate() {
		return this.memshipdate;

	}

	public void setMemshipdate(Date memshipdate) {
		this.memshipdate = memshipdate;
	}

	private String viptype;

	public String getViptype() {
		return this.viptype;

	}

	public void setViptype(String viptype) {
		this.viptype = viptype;
	}

	private String packagename;

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	private String usersource;

	public String getUsersource() {
		return usersource;
	}

	public void setUsersource(String usersource) {
		this.usersource = usersource;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date tvdate;

	public final Date getTvdate() {
		return tvdate;
	}

	public final void setTvdate(Date tvdate) {
		this.tvdate = tvdate;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	private String lastloginip;

	public final String getLastloginip() {
		return lastloginip;
	}

	public final void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	private String yqr;
	private String regip;
	private String regapmac;

	public String getYqr() {
		return yqr;
	}

	public void setYqr(String yqr) {
		this.yqr = yqr;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public String getRegapmac() {
		return regapmac;
	}

	public void setRegapmac(String regapmac) {
		this.regapmac = regapmac;
	}

	private boolean user_has_charge;

	public boolean isUser_has_charge() {
		return user_has_charge;
	}

	public void setUser_has_charge(boolean user_has_charge) {
		this.user_has_charge = user_has_charge;
	}

	private String user_tv_viptype;

	public String getUser_tv_viptype() {
		return user_tv_viptype;
	}

	public void setUser_tv_viptype(String user_tv_viptype) {
		this.user_tv_viptype = user_tv_viptype;
	}

	private boolean user_has_buypackage = false;
	private boolean user_has_yqbouns = false;

	public boolean isUser_has_buypackage() {
		return user_has_buypackage;
	}

	public void setUser_has_buypackage(boolean user_has_buypackage) {
		this.user_has_buypackage = user_has_buypackage;
	}

	public boolean isUser_has_yqbouns() {
		return user_has_yqbouns;
	}

	public void setUser_has_yqbouns(boolean user_has_yqbouns) {
		this.user_has_yqbouns = user_has_yqbouns;
	}

	private Integer usertype;// 1:普通用户,2:预注销用户

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "AppUser [version=" + version + ", location_id=" + location_id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", points=" + points + ", points_converted=" + points_converted
				+ ", points_earned=" + points_earned + ", status=" + status + ", salt=" + salt + ", gender=" + gender
				+ ", email=" + email + ", modified_datetime=" + modified_datetime + ", created_datetime="
				+ created_datetime + ", last_login=" + last_login + ", help_recharge=" + help_recharge
				+ ", bind_mobile=" + bind_mobile + ", mobile=" + mobile + ", template_name=" + template_name
				+ ", memshipdate=" + memshipdate + ", viptype=" + viptype + ", packagename=" + packagename
				+ ", balance=" + balance + ", usersource=" + usersource + ", tvdate=" + tvdate + ", lastloginip="
				+ lastloginip + ", yqr=" + yqr + ", regip=" + regip + ", regapmac=" + regapmac + ", user_has_charge="
				+ user_has_charge + ", user_tv_viptype=" + user_tv_viptype + ", user_has_buypackage="
				+ user_has_buypackage + ", user_has_yqbouns=" + user_has_yqbouns + ", usertype=" + usertype + "]";
	}

	private String fieldlock;

	public String getFieldlock() {
		if (StringTool.isNullOrEmpty(fieldlock)) {
			return new Date().getTime() + "";
		}
		return fieldlock;
	}

	public void setFieldlock(String fieldlock) {
		this.fieldlock = fieldlock;
	}

	private String updatelock;

	public String getUpdatelock() {
		return updatelock;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date temporarydate;

	public Date getTemporarydate() {
		return temporarydate;
	}

	public void setTemporarydate(Date temporarydate) {
		this.temporarydate = temporarydate;
	}

	public void setUpdatelock(String updatelock) {
		this.updatelock = updatelock;
	}
	private int id;
	public  int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private  String userheadimage;

	public String getUserheadimage() {
		return userheadimage;
	}

	public void setUserheadimage(String userheadimage) {
		this.userheadimage = userheadimage;
	}
}