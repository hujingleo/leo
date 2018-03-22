package com.youxin.ymall.jifenutils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 生成免登录URL
 */
public class RestApiAutoLoginSign {
	private String appKey = "368a9af2db5a4a3741015a5a57d1250002";
	private String appSecret = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxEpWCL5rLUByt6WPsH6kW9PpSZVxE/x/kxWfAa8NWHESLH7RO984yPy8jOyQH1SClb70rDkHMEZJWgxGWcZ7QoOZHmr2NLtj9bR6ZxMC8/KdzCbY51IYk22Ti88RKvvPMVSVgS3Vv/uwvpyHLGmTi5RXIZe2/nJYK+bTlDo31fwIDAQAB";
	// private String requestUri =
	// "http://open-home.teshehui.com/tsh-api/index.html";
	private String requestUri = "www.cocosurprise.com/tsh-api/index.html";

	public String getAutoLoginUrl(String uid, String credits) {

		if (!requestUri.startsWith("http")) {
			requestUri = "http://" + requestUri;
		}
		Map<String, Object> params = new LinkedHashMap();
		params.put("appKey", appKey);
		params.put("appSecret", appSecret);
		params.put("timestamp", System.currentTimeMillis());
		params.put("uid", uid);
		params.put("credits", credits); // 当前用户的积分余额数

		Map<String, Object> stringObjectMap = RequestUtil.sortMapByKey(params);
		String signStr = RequestUtil.encodeParameterValues(stringObjectMap);
		String sign = MD5Util.getMd5(signStr);
		params.put("sign", sign);
		params.remove("appSecret");
		String linkStr = RequestUtil.encodeParameter(params);

		requestUri = requestUri + "?" + linkStr;
		System.out.println("========requestUri:" + requestUri);
		return requestUri;
	}
}
