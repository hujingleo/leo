package com.youxin.ymall.jifenutils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by jackzhong on 8/19/16.
 */
public final class RequestParamsUtils {

	public static Map<String, Object> getParameters(HttpServletRequest request) {
		if (request == null) {
			return new HashMap();
		}
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		String prefix = "";
		while ((paramNames != null) && paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			// 去除签名值KEY
			if (SignUtils.params_sign.equals(paramName)) {
				continue;
			}
			String unprefixed = paramName.substring(prefix.length());
			String[] values = request.getParameterValues(paramName);
			if ((values == null) || (values.length == 0)) {
			} else if (values.length > 1) {
				params.put(unprefixed, values);
			} else {
				params.put(unprefixed, values[0]);
			}
		}
		return params;
	}

	public static String encodeParameter(Map<String, Object> params) {
		try {
			if (params == null || params.isEmpty()) {
				return "";
			}
			StringBuilder queryStringBuilder = new StringBuilder();
			Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				queryStringBuilder.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
			}
			return queryStringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}

		Map<String, Object> map = new TreeMap<String, Object>(new Comparator<String>() {
			public int compare(String obj1, String obj2) {
				// 升序排序
				return obj1.compareTo(obj2);
			}
		});
		map.putAll(oriMap);
		return map;
	}
}
