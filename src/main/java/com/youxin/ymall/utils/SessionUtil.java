package com.youxin.ymall.utils;

import javax.servlet.http.HttpSession;

import com.rsclouds.util.StringTool;
import com.youxin.ymall.entity.AppUserInfo;
import com.youxin.ymall.exceptions.SessionException;

public class SessionUtil {
	public static String SESSION_APPUSERINFO = "SESSION_APPUSERINFO";

	/**
	 * 获取当前登录的用户名
	 * 
	 * @param session
	 * @return
	 * @throws SessionException
	 */
	public static String getCurrentUserName(HttpSession session) {

		AppUserInfo userInfo = (AppUserInfo) session.getAttribute(SESSION_APPUSERINFO);
		if (userInfo == null || StringTool.isNullOrEmpty(userInfo.getUsername())) {
			throw new SessionException();
		} else
			return userInfo.getUsername();

	}

	public static String getCurrentDisplayName(HttpSession session) {

		AppUserInfo userInfo = (AppUserInfo) session.getAttribute(SESSION_APPUSERINFO);
		if (userInfo == null || StringTool.isNullOrEmpty(userInfo.getUsername())) {
			throw new SessionException();
		} else {
			String truename = userInfo.getTruename();
			if (StringTool.isNullOrEmpty(truename))
				return userInfo.getUsername();
			else
				return truename;
		}

	}
}
