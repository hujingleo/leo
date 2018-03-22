package com.youxin.ymall.entity;

import com.youxin.ymall.entity.AppUser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * app session存储使用
 * 
 * @author Administrator
 *
 */
public class AppUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7614998612696819430L;
	private String username;
	private String truename;
	private int points;
	private BigDecimal balance;
	private Date wifidate;
	private String packagename;
	private String type;
	private Date tvdate;
	private String tvtype = "PAY";

	public final Date getTvdate() {
		return tvdate;
	}

	public final void setTvdate(Date tvdate) {
		this.tvdate = tvdate;
	}

	public AppUserInfo() {
	}

	public AppUserInfo(AppUser appuser) {
		this.username = appuser.getUsername();
		this.truename = appuser.getName();
		this.points = appuser.getPoints();
		this.balance = appuser.getBalance();
		this.wifidate = appuser.getMemshipdate();
		this.packagename = appuser.getPackagename();
		this.type = appuser.getViptype();
		this.tvtype = appuser.getUser_tv_viptype();
		this.tvdate = appuser.getTvdate();
		this.userheadimage=appuser.getUserheadimage();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getWifidate() {
		if (wifidate == null || wifidate.before(new Date())) {
			return null;
		}
		return wifidate;
	}

	public void setWifidate(Date wifidate) {
		this.wifidate = wifidate;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTvtype() {
		return tvtype;
	}

	public void setTvtype(String tvtype) {
		this.tvtype = tvtype;
	}

	public String userheadimage;

	public String getUserheadimage() {
		return userheadimage;
	}

	public void setUserheadimage(String userheadimage) {
		this.userheadimage = userheadimage;
	}
}
