package com.youxin.ymall.jifenutils;

import java.util.Map;

/**
 * Created by jackzhong on 8/19/16.
 */
public final class SignUtils {

	private static AppInfo appInfo = null;
	public static final String params_appSecret = "appSecret";
	public static final String params_sign = "sign";

	static {
		appInfo = new AppInfo("368a9af2db5a4a3741015a5a57d1250002",
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxEpWCL5rLUByt6WPsH6kW9PpSZVxE/x/kxWfAa8NWHESLH7RO984yPy8jOyQH1SClb70rDkHMEZJWgxGWcZ7QoOZHmr2NLtj9bR6ZxMC8/KdzCbY51IYk22Ti88RKvvPMVSVgS3Vv/uwvpyHLGmTi5RXIZe2/nJYK+bTlDo31fwIDAQAB");

	}

	public static AppInfo getAppInfo() {
		return appInfo;
	}

	public static final String getSignValue(Map<String, Object> params) {
		params.put(params_appSecret, appInfo.getAppSecret());
		// 排序请求参数
		Map<String, Object> stringObjectMap = RequestParamsUtils.sortMapByKey(params);
		// 组装签名字符串
		String signStr = RequestParamsUtils.encodeParameter(stringObjectMap);
		// 生成签名值
		String sign = MD5Util.getMd5(signStr);
		params.remove(params_appSecret);
		return sign;
	}
}
