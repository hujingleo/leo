package com.youxin.ymall.utils;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class RSATester {

	static String publicKey;
	static String privateKey;

	static {
		try {
			Map<String, Object> keyMap = RSAEncrypUtil.genKeyPair();
			// publicKey = RSAEncrypUtil.getPublicKey(keyMap);
			// privateKey = RSAEncrypUtil.getPrivateKey(keyMap);
			/**
			 * 可使用openssl生成公钥，私钥 1.生成私钥 genrsa -out rsa_private_key.pem 1024
			 * 2.生成公钥 rsa -in rsa_private_key.pem -pubout -out
			 * rsa_public_key.pem 3.生成PKCS8编码的私钥 pkcs8 -topk8 -inform PEM -in
			 * rsa_private_key.pem -outform PEM -nocrypt
			 * 4.在java程序中使用时，即使用第二步骤中生成作为公钥，第三步中内容作为私钥
			 */
			publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqOr7k4xvPyMRt9c9C9A7buC+F1m/92YX3iqZ/BoYOSE2ea08Ztyc1jq7fMrMYet3amutuKkIg6/FsooyFcI14hVHkpvmvOLWRbOyu0FmNNSuKxHWFowVULxw93koQb3DWoNbfnGy/uuWmch1GZ37tIrxKG1kgRgCdUQrHywQBdwIDAQAB";
			privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOo6vuTjG8/IxG31z0L0Dtu4L4XWb/3ZhfeKpn8Ghg5ITZ5rTxm3JzWOrt8ysxh63dqa624qQiDr8WyijIVwjXiFUeSm+a84tZFs7K7QWY01K4rEdYWjBVQvHD3eShBvcNag1t+cbL+65aZyHUZnfu0ivEobWSBGAJ1RCsfLBAF3AgMBAAECgYA1WzZ7C2Udex2L8u/Iz7HGyXlB4qxWRkPnNNVioEjPXhcYXFeDgx5Sa/NX8sOrcumwz5OL3+J6f2Tam1ipWQ9Qkm3ZaqWb74aXweJeiSR0MFH8CCU6Noa6i4kqhJpj0sWlxXYARGZbmptjxR7Fe5rNi3oo1z3H29C0Hl9w0wXhIQJBAPrGO9ETthJoHZ0GBpb6s6dIrhGpFtp4MqWN8rOAtaSrZHoajQucwgFrEJ4NQThKo6aSORW2ZhQG9cIwrJmz2icCQQDvHECwMJcy6g8Gr6glkJ/SRM3y9hiItf45tA1ezCja1Kny0N7LNQ90u7kyEypEydkPG/LIlCZjSGGgukJidsAxAkBFva+Q+7xc7hueObjHcD1aPno6ax3x8A+Vvx5KEXdyrj+pPY0QN640msPqUKFcuFU+09eQVEObOjxKnyLBNCVtAkBSWJ6GjxVjOWxXVyNHXJlN5tgudkZYvqSA5ts77H+dbWPh9cDkpq9d+lB7SFJkQkd4hp2EKlFWG9VTbxHxqwfhAkBd5/yS3uC/YeYAJQWAGfQDfxmrPQjsgyRwlUMiTYAeiOqBElcz8f0minTfTrbIc9dVO4g6TsyIZB0z9sddV8Vv";

			System.err.println("公钥: " + publicKey);
			System.err.println("私钥： " + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		test();
		// testSign();
	}

	public static void test() throws Exception {
		// String result =
		// RSAEncrypUtil.RSADecode(privateKey,"YY8ldYyG7zewUrVc3O6l3jH47yH6i+Qpj56oIUcgqCHwN+uL/nWgMMMqADNM6G+0VZipFA4Er+R5ANxyBknNtBuZxXnwhwgIrzN1TvQ47edJuuFpGgIVbBtpc+QqgnvH9CjIlp+NA7vnrWJ06BJBnNhWMhTsXilFWDSISSSG05k=");
		String result = RSAEncrypUtil.RSAEncode(System.currentTimeMillis() + "@fdu", publicKey);
		System.out.println(result);
	}

	// public static void testSign() throws Exception {
	// System.err.println("私钥加密——公钥解密");
	// String source = "这是一行测试RSA数字签名的无意义文字";
	// System.out.println("原文字：" + source);
	// byte[] data = source.getBytes();
	// byte[] encodedData = RSAEncrypUtil.encryptByPrivateKey(data, privateKey);
	// System.out.println("加密后：" + new String(encodedData));
	// byte[] decodedData = RSAEncrypUtil.decryptByPublicKey(encodedData,
	// publicKey);
	// String target = new String(decodedData);
	// System.out.println("解密后: " + target);
	//
	//
	// System.err.println("私钥签名——公钥验证签名");
	// String sign = RSAEncrypUtil.sign(encodedData, privateKey);
	// System.err.println("签名:\r" + sign);
	// boolean status = RSAEncrypUtil.verify(encodedData, publicKey, sign);
	// System.err.println("验证结果:\r" + status);
	// }

}