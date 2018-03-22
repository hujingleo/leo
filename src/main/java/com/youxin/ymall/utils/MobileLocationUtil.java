package com.youxin.ymall.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.rsclouds.base.SimpleNetObject;

public class MobileLocationUtil {

	/**
	 * 获取URL返回的字符串
	 * 
	 * @param callurl
	 * @param charset
	 * @return
	 */
	private static String callUrlByGet(String callurl, String charset) {
		String result = "";
		try {
			URL url = new URL(callurl);
			URLConnection connection = url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
				result += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	/**
	 * 手机号码归属地
	 * 
	 * @param tel
	 *            手机号码
	 * @return 135XXXXXXXX,联通/移动/电信,湖北武汉
	 * @throws Exception
	 * @author
	 */
	/*
	 * public static SimpleNetObject getMobileLocation(String tel) {
	 * SimpleNetObject sno = new SimpleNetObject(); try { Pattern pattern =
	 * Pattern.compile("1\\d{10}"); Matcher matcher = pattern.matcher(tel); if
	 * (matcher.matches()) { String url =
	 * "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+ tel; String
	 * result = callUrlByGet(url, "GBK"); StringReader stringReader = new
	 * StringReader(result); InputSource inputSource = new
	 * InputSource(stringReader); DocumentBuilderFactory documentBuilderFactory
	 * = DocumentBuilderFactory .newInstance(); DocumentBuilder documentBuilder
	 * = documentBuilderFactory .newDocumentBuilder(); Document document =
	 * documentBuilder.parse(inputSource); System.out.println("document : " +
	 * document); String retmsg =
	 * document.getElementsByTagName("retmsg").item(0)
	 * .getFirstChild().getNodeValue(); if (retmsg.equals("OK")) { String
	 * supplier =
	 * document.getElementsByTagName("supplier").item(0).getFirstChild().
	 * getNodeValue().trim(); String province =
	 * document.getElementsByTagName("province").item(0).getFirstChild().
	 * getNodeValue().trim(); String city =
	 * document.getElementsByTagName("city").item(0).getFirstChild().
	 * getNodeValue().trim(); if(StringTools.nil(province) ||
	 * StringTools.nil(city) || ("-").equals(province) || ("-").equals(city)) {
	 * sno.setResult(1); sno.setMessage(getMobileAddress(tel)+","+supplier);
	 * return sno; } else { sno.setResult(1); sno.setMessage(province + "," +
	 * city + "," + supplier); return sno; } } else { sno.setResult(97);
	 * sno.setMessage("很抱歉！没有该手机号的归属地记录！"); return sno; } } else {
	 * sno.setResult(98); sno.setMessage("手机号码格式错误！"); return sno; } } catch
	 * (Exception e) { e.printStackTrace(); sno.setResult(99);
	 * sno.setMessage("很抱歉！查询归属地异常,请稍后重试!"); } return sno; }
	 */

	/**
	 * 手机号码归属地
	 * 
	 * @param tel
	 *            手机号码
	 * @return 135XXXXXXXX,联通/移动/电信,湖北武汉
	 * @throws Exception
	 * @author
	 */
	public static SimpleNetObject getMobileLocation(String tel) {
		SimpleNetObject sno = new SimpleNetObject();
		try {
			Pattern pattern = Pattern.compile("1\\d{10}");
			Matcher matcher = pattern.matcher(tel);
			if (matcher.matches()) {
				String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + tel;
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(callUrlByGet(url, "GBK").trim().replace("'", ""));
				String jsonStr = m.replaceAll("");
				Map<String, String> map = json2map(jsonStr);
				System.out.println(map.get("carrier"));
				if ("广东移动".equals(map.get("carrier"))) {
					sno.setResult(1);
					sno.setMessage(map.get("carrier"));
				} else {
					sno.setResult(2);
					sno.setMessage("暂不支持充值您的手机号哦!");
				}

			} else {
				sno.setResult(98);
				sno.setMessage("手机号码格式错误！");
				return sno;
			}
		} catch (Exception e) {
			e.printStackTrace();
			sno.setResult(99);
			sno.setMessage("很抱歉！查询归属地异常,请稍后重试!");
		}
		return sno;
	}

	// 查询手机号码所属地区
	public static String getMobileAddress(String mobile) throws Exception {
		String address = "";
		try {
			mobile = mobile.trim();
			String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + mobile;
			URLConnection connection = (URLConnection) new URL(url).openConnection();
			connection.setDoOutput(true);
			InputStream os = connection.getInputStream();
			Thread.sleep(100);
			int length = os.available();
			byte[] buff = new byte[length];
			os.read(buff);
			String s = new String(buff, "gbk");
			int len = s.indexOf("卡号归属地");
			s = s.substring(len, len + 100);
			len = s.lastIndexOf("</TD>");
			address = s.substring(0, len);
			len = address.lastIndexOf(">");
			address = address.substring(len + 1, address.length());
			address = address.replace("&nbsp;", ",");
			address = address.replace("d> -->", "");
			address = address.replace(" -->", "");
			address = address.replace("-->", "");
			s = null;
			buff = null;
			os.close();
			connection = null;

		} catch (Exception e) {
			address = "未知";
			System.out.println("手机所属地查询失败====================");
		}
		return address;
	}

	public static Map<String, String> json2map(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		int start = jsonStr.indexOf("{") + 1;
		int end = jsonStr.indexOf("}");
		String subJsonStr = jsonStr.substring(start, end);
		for (String temp1 : subJsonStr.split(",")) {
			String[] temp = temp1.split(":");
			map.put(temp[0], temp[1]);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(getMobileLocation("13543909768"));
	}

}