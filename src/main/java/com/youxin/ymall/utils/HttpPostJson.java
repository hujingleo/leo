package com.youxin.ymall.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.youxin.ymanage.entity.LanFQFactoryInfo;

/**
 * Description:发送HttpPost json请求
 * 
 * @author ljw 2016-6-3
 * @version v1.0
 */
public class HttpPostJson {

	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String post(String strURL, String params) {
		System.out.println(strURL);
		System.out.println(params);
		try {
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // utf-8编码
				System.out.println(result);
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ""; // 自定义错误信息
	}

	public static void main(String[] args) {
		/*
		 * post("http://www.lanfq.net/api/interface/Factory?ltype=1",
		 * "{\"Factorys\": [{\"FTYID\": \"0\",\"FTYName\": \"未分配\",\"FTYAddress\":\"未分配工厂\"}]}"
		 * );
		 */

		Gson gson = new Gson();
		List<LanFQFactoryInfo> list = new ArrayList<LanFQFactoryInfo>();
		LanFQFactoryInfo lfq = new LanFQFactoryInfo();
		lfq.setFTYID("0");
		lfq.setFTYName("未分配");
		lfq.setFTYAddress("未分配工厂");
		list.add(lfq);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Factorys", list);
		String requestJson = gson.toJson(map);
		System.out.println(requestJson);
		post("http://www.lanfq.net/api/interface/Factory?ltype=1",
				"{\"Factorys\": [{\"FTYID\": \"0\",\"FTYName\": \"未分配\",\"FTYAddress\":\"未分配工厂\"}]}");
	}

}
