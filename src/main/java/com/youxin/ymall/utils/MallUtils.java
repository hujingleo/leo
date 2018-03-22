package com.youxin.ymall.utils;

import com.rsclouds.util.DateUtil;
import com.rsclouds.util.StringTool;

public class MallUtils {
	public static String createOrderCode() {
		return DateUtil.getCurrentDate("yyyyMMddHHmmss") + StringTool.getRamCode(6);
	}
}
