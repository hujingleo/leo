package com.youxin.ymall.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * RSA公钥/私钥/签名工具包 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 *
 */
public class RSAEncrypUtil {

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 公钥的key
	 */
	// public static final String PUBLIC_KEY =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWbLzvIiYC/kfFBnSIy6TnVNo39HbKW2FsSTVuXPVwJEczp2rgBcn2joQgNQpQtjn9Pselo5yyo8/ZmWSlg7jGFp4/79LR77kHKRz5RLC3tnBJ1vH60MCCGwdHmnCFU+r4l/7iRLaGNLVqDUl7eT3oJ8gIbqEoRoaoFEBOl1DPfQIDAQAB";
	public static final String PUBLIC_KEY = "AAAAB3NzaC1yc2EAAAADAQABAAAAgQCP8yeO3qH/Ci3FwaSdHC91uCgw9Bz0HOxhzl+by0RYamDsb5mvBdI/zbGFFfWZi55g0CUZjP/BTas3srAf9gjznpV7Gg4YeIdTJBvM3jf3u/Qnu/Y403XyDGO6UPc1EV5xIm0IvwAv42dgZybL+1pLjq1Qsti41z7FHE7xnNSCTw==";

	/**
	 * 私钥的key
	 */
	// public static final String PRIVATE_KEY =
	// "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANZsvO8iJgL+R8UGdIjLpOdU2jf0dspbYWxJNW5c9XAkRzOnauAFyfaOhCA1ClC2Of0+x6WjnLKjz9mZZKWDuMYWnj/v0tHvuQcpHPlEsLe2cEnW8frQwIIbB0eacIVT6viX/uJEtoY0tWoNSXt5PegnyAhuoShGhqgUQE6XUM99AgMBAAECgYEAsLa+4qmXACKGDXdrdQ234rqIeVLsz4qN+MtA0Kqlz/zl/Uj9iJDcyGjdtRlhXfFAS1iA+x6i1oqwkpmbW9j53ixJax62d/a6FWH1WBuqc45Zkjs8fdSSaVjf7OqgYOWudQS+6SdquCtRcTQsb3L/+kodLKrCVXPRSsOuGO/NyfUCQQD1uuuOg7zGSWaDPOuKA8qvR2g4M+95Ajf/xwflAm9Ocj1Ny9C//jKVwmM4Db6eXAUQWGaFr3lnvjd6dIkONv47AkEA32LhOM86j//0AsuI6nk1ivBmP9gJc4bYDn60s+SXlmv2zS76fbXjOy3MJGJzAdsDVfc5aF5BkMlUArTyWcB1pwJBAJSrwcbY14bUc+xlGFDJWIDR4DFY4sNA32amIZWh8nvwzDxcWvE+N5a6JFOOpUykef/YrDan4vkscd5p0V5DUW8CQQCQbHpWY0xme0HEaNj9Vx4x1NkWn9MEeQLI5ExqHfmPi//FioDys5zUzvgz8d6PLY0LkEnyWzYiqLeDBH/HXTHzAkEAm9//h02POTWXSxVYxYf2B6tFJp4NxB9MIblm5CoeXc8yOuS+YPkIOxdw630mMxh6Ye1G2DcQ2pFyTtrK0FZoTQ==";

	public static final String PRIVATE_KEY = "pQ5XbuG0Ei1MfEsbtcZ80Z7HVwZSZWhyyy0afNWpuLFsYLcOY5+7a8zsI6WRWUWr"
			+ "LAMMEC6fvpSKCTVNVxKo7+WNt3ungWhqmiDbXn041bNaQHF8b7g/BZzXShqFxFCa"
			+ "jgFzqMVHYOGLW8Ns2VieA4025O6lcn6CJ/jUvBRohnb3JNprp7Q+Ns6hchhb8uN0"
			+ "w9aTv49fmrwksO6lsO27C3ZxXngE26IrRPfOXe70rZqC3bBCnLP6vdwcE6bVW2I4"
			+ "th+Gj6Fm+/j27rvXobASN37UjYWf33xsKUzJqDeLoNOZ2F5wea2M3Llf96z3zh3H"
			+ "tXO0J8+BVc8HfQxObMAJhzHPaIhsdqvxeZLOVzuAZgxVHoCGoMecZorHti5zFrA6"
			+ "FcAxAVKq3IebRQgFtlLUgZFe5oQTrfuBYOHlR4SgW+vF557QSLiO13C8aCZJGFtv"
			+ "CpAMpcrM91iGS+GTCLyGnFaqtn4VeaR7QR+MVdlQa+8SJrtRUEkNKbd34rPw5QuR"
			+ "nAJW8d4CFqNOR/5jf0PnRftEQ/iEwcVb1PEdD2YRTyM23xbsoovzvHsNtntBBPux"
			+ "VaLM1t8kivO/UATzePTKSWRa+05bc2XdMi6L9FeJG3SRxTxOmJIRd3iD/7klOyJL"
			+ "DWW1l/p2XGzRRZq7MZs+8JZXe8tVFU80pS9R2Vj7lQ9RybV9abCPedZQhq7Xb4ih"
			+ "Khgygz9AFM9ahaEs+AIMAlny8Y4YyZO54OQvjHnEUczJTTTVhb/6jj8ClsFJzu0V"
			+ "Ai8jhv0gC5Bq+Die2uoFx/HthQDKmzJsIuYqFO+ZNPc=";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 生成密钥对(公钥和私钥)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);// 初始大小
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}

	/**
	 * 
	 * 校验数字签名
	 * 
	 * 
	 * @param data
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @param sign
	 *            数字签名
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decodeBase64(sign));
	}

	/**
	 * 
	 * 私钥解密
	 * 
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 
	 * 公钥解密
	 * 
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 
	 * 公钥加密
	 * 
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 
	 * 私钥加密
	 * 
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	// /**
	// *
	// * 获取私钥
	// *
	// *
	// * @param keyMap 密钥对
	// * @return
	// * @throws Exception
	// */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encodeBase64String(key.getEncoded());
	}
	//
	// /**
	// *
	// * 获取公钥
	// *
	// *
	// * @param keyMap 密钥对
	// * @return
	// * @throws Exception
	// */
	// public static String getPublicKey(Map<String, Object> keyMap)
	// throws Exception {
	// Key key = (Key) keyMap.get(PUBLIC_KEY);
	// return Base64.encodeBase64String(key.getEncoded());
	// }

	/**
	 * 对数据加密 返回经过Base64编码的加密字符串
	 * 
	 * @param data
	 *            需要加密的数据
	 * @param publicKey
	 *            公钥
	 * @return
	 */
	public synchronized static String RSAEncode(String data, String publicKey) throws Exception {
		// 加密后的byte数组
		byte[] encryptDataBytes = encryptByPublicKey(data.getBytes("UTF-8"), publicKey);
		// 经过base64编码
		return Base64.encodeBase64String(encryptDataBytes);
	}

	/**
	 * 数据解密 数据是经过Base64编码的加密字符串 返回解密后的字符串
	 * 
	 * @param version
	 * @param data
	 * @return
	 */
	public synchronized static String RSADecode(String privateKey, String data) throws Exception {
		// 加密数据进过Base64解码
		byte[] decodeBytes = Base64.decodeBase64(data);
		// 通过私钥对数据解密
		byte[] decryptData = decryptByPrivateKey(decodeBytes, privateKey);
		return new String(decryptData, "UTF-8");
	}

	public synchronized static String RSADecode2(String privateKey, String data) throws Exception {
		// 加密数据进过Base64解码
		byte[] decodeBytes = Base64.decodeBase64(data);
		// 通过私钥对数据解密
		byte[] decryptData = decryptByPrivateKey(decodeBytes, privateKey);
		return new String(decryptData, "UTF-8");
	}
}