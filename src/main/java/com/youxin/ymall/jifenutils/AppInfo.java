package com.youxin.ymall.jifenutils;

/**
 * Created by jackzhong on 8/19/16.
 */
public class AppInfo {

	private String appKey;
	private String appSecret;

	public AppInfo(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}
}
