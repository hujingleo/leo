package com.youxin.ymall.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;

import com.youxin.ymall.entity.ZosResult;

/**
 * Description:zos工具类,包括加密解密,发送网络请求
 * 
 * @author ljw 2016-3-10
 * @version v1.0
 */
public class ZosUtils {
	private static int connectTimeOut = 5000;// 连接超时时间
	private static int readTimeOut = 5000;// 获取响应时间
	private static String requestEncoding = "UTF-8";

	private String rsaPublicKey;
	private byte[] rsaPublicKeyByte;
	private String rsaPrivateKey;
	private byte[] rsaPrivateKeyByte;
	private String rsaversion;

	public ZosUtils(String rsaPublicKey, String rsaPrivateKey, String rsaversion) {
		// System.out.println(rsaPublicKey);
		// System.out.println(rsaPrivateKey);
		// System.err.println(rsaversion);
		this.rsaPublicKey = rsaPublicKey;
		this.rsaPublicKeyByte = Base64.decodeBase64(rsaPublicKey);
		this.rsaPrivateKey = rsaPrivateKey;
		this.rsaPrivateKeyByte = Base64.decodeBase64(rsaPrivateKey);
		this.rsaversion = rsaversion;
	}

	/**
	 * Description：传入参数，请求zos的api
	 * 
	 * @param reqUrl
	 * @param parameters
	 * @return
	 */
	public ZosResult requestZosApi(String reqUrl, Map<String, String> parameters) {
		ZosResult zosResult = new ZosResult();
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) iter.next();
				paraMap.put(element.getKey().toString(),
						RSAEncrypUtil.RSAEncode(element.getValue().toString(), this.rsaPublicKey));
			}

			Map<String, String> headMap = new HashMap<String, String>();
			headMap.put("version", this.rsaversion);
			headMap.put("code", RSAEncrypUtil.RSAEncode(System.currentTimeMillis() + "@fdu", this.rsaPublicKey));
			String result = HttpClientUtil.getPostResponse(reqUrl, paraMap, headMap);
			System.out.println("zos请求返回字符串结果 : \n" + result);
			ObjectMapper objectMapper = new ObjectMapper();
			zosResult = objectMapper.readValue(result, ZosResult.class);
		} catch (Exception e) {
			e.printStackTrace();
			zosResult.setStatus(-1);
			zosResult.setMsg("解析请求结果失败");
		}
		return zosResult;
	}

	/**
	 * Description：post请求
	 * 
	 * @param reqUrl
	 * @param parameters
	 * @return
	 * @throws Exception
	 *
	 */
	public String doPost(String reqUrl, Map<String, String> parameters) throws Exception {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(RSAEncrypUtil.RSAEncode(element.getValue().toString(), this.rsaPublicKey));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setRequestProperty("version", rsaversion);
			String codeString = new Date().getTime() + "@fdu";
			url_con.setRequestProperty("code", RSAEncrypUtil.RSAEncode(codeString, this.rsaPublicKey));
			System.out.println(codeString);
			System.out.println(encodeRsaString(codeString, this.rsaPublicKeyByte));
			url_con.setConnectTimeout(connectTimeOut);
			url_con.setReadTimeout(readTimeOut);
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			byte[] echo = new byte[10 * 1024];
			int len = in.read(echo);
			responseContent = (new String(echo, 0, len)).trim();
			int code = url_con.getResponseCode();
			if (code != 200) {
				responseContent = "ERROR" + code;
			}

		} catch (IOException e) {

			System.out.println("网络故障:" + e.toString());
			throw e;
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}

	/**
	 * Description：根据rsa公钥对字符串进行加密 @return @throws Exception @throws
	 */
	public String encodeRsaString(String originalString, byte[] publicKeyArray) throws Exception {
		// 得到公钥
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyArray);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey keyPublic = kf.generatePublic(keySpec);
		// 加密数据
		Cipher cp = Cipher.getInstance("RSA");
		cp.init(Cipher.ENCRYPT_MODE, keyPublic);
		byte[] result = cp.doFinal(originalString.getBytes());
		result = Base64.encodeBase64(result);
		String resultString = "";
		for (byte b : result) {
			resultString += b;
		}
		return resultString;
	}

	/**
	 * Description：使用私钥进行解密
	 * 
	 * @param encryptedDataArray
	 * @throws Exception
	 */
	public String decodeRsaString(String str, byte[] privateKey) throws Exception {
		byte[] encryptedDataArray = str.getBytes();
		// 得到私钥
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey keyPrivate = kf.generatePrivate(keySpec);

		// 解密数据
		Cipher cp = Cipher.getInstance("RSA");
		cp.init(Cipher.DECRYPT_MODE, keyPrivate);
		byte[] arr = cp.doFinal(encryptedDataArray);
		arr = Base64.decodeBase64(arr);
		// 得到解密后的字符串
		return new String(arr);
	}

	public static void main(String[] args) {
		ZosUtils zosUtils = new ZosUtils(
				"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJKzLFBYZSEMe31dI72r8qpUc7zJqGqxk/prjOXcZMHtBsC4rlefIMcq/CgW1MHC1vzhdXENAVQdsYAJ49BjevNn2Yg6wqd+o1mkzvNW72bjywSUyBFDFeCYbmLNuObVK/o4AsoS/KHm8Q17MjPdzvu+VL5R9jjSHz+dLQstWXjQIDAQAB",
				"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIkrMsUFhlIQx7fV0jvavyqlRzvMmoarGT+muM5dxkwe0GwLiuV58gxyr8KBbUwcLW/OF1cQ0BVB2xgAnj0GN682fZiDrCp36jWaTO81bvZuPLBJTIEUMV4JhuYs245tUr+jgCyhL8oebxDXsyM93O+75UvlH2ONIfP50tCy1ZeNAgMBAAECgYBf5q/sR9yNa+X2hawi5OFa84tGJ9qx5NYWRn5W6gFGHxUhNUC7B/NO+LAhkVPVLq4lyTVQThTth8mikmwPxlUPSN6c/Tn4zDfvVUaqt3jpBe3hhG/lH/UQqZX7PfGwtrSH1iXyQkDYhisQdLotLRtO+R7nbd+3NqTAXf/ewN78AQJBAMYS0Lm2hkTxcVDZoLiVc74bN3YSrywzBbDiQkeJMK5yIQ7Br5eDn5fsoyQjW0HBCoHiNKzX6GNTXT3J7HGO3sECQQCxSJ8u0sRHcE93KfWeaz26Qrq3o+BYFJm+tymPvqx0RZMw1k84/SnLHwIvT9q3eX1fH0TUEvqUivIrIHy7wffNAkEAoRI/5iXFvl7RFTxEdJYkTx321e+Pv9Ol9xenamDn05Q/ya47ymxg5byrVgHiffArST/YMg5JjiwGkO6ZdicGgQJAMIFJJFuAXndpFpaBYF3BHtQhGBubRx4jzp14np6xuoJKumL4fAQwSiXqyB0WH3r7BWqdv1SOANRfW9NgrVJtxQJAIe71cWcaWqWK+z1WVRVFfOJdrSjKmYv82AJHvTiVlAnP+M8U4AtQ5iKLbtMzV6LpjD0ZuN9w9fdb6fWuBlPwlA==",
				"v1.1");
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("userName", "13431371907");
		parameters.put("password", "111111");
		parameters.put("realName", "小明");
		parameters.put("userGroupName", "套餐用户");
		parameters.put("templateName", "套餐");
		/*
		 * ZosResult zosResult = zosUtils.requestZosApi(
		 * "http://192.168.3.33:8080/z-os/api/v1/user/addUser" +
		 * "?userName=test1&password=111111&realName=小名&userGroupName=套餐用户&templateName=套餐",
		 * parameters);
		 */
		// ZosResult zosResult =
		// zosUtils.requestZosApi("http://192.168.3.33:8080/z-os/api/v1/user/addUser",
		// parameters);

		ZosResult zosResult = zosUtils.requestZosApi("http://192.168.3.33:8080/z-os/api/v1/user/addUser", parameters);
		System.out.println(zosResult.getMsg());

	}
}
