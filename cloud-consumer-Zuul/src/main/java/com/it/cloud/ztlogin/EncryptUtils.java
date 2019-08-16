package com.it.cloud.ztlogin;

import com.it.cloud.ztfilter.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 编码工具类 实现aes加密、解密
 */
public class EncryptUtils {
	private EncryptUtils() {
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);

	/**
	 * 密钥 32位
	 */
	private static final String KEY = Constants.KEY;

	/**
	 * 算法
	 */
	private static final String ALGORITHMSTR_ECB = Constants.ALGORITHMSTR_ECB;

	private static final String ALGORITHMSTR = Constants.ALGORITHMSTR_CBC;

	private static final String VI = Constants.VI;

	public static void main(String[] args) throws Exception{

		String encrypt = EncryptUtils.encrypt("123456",Constants.KEY);
		System.out.println(encrypt);
		System.out.println(encrypt.length());
		System.out.println(EncryptUtils.Decrypt(encrypt,Constants.KEY));

	}
	public static String toHexString1(byte[] b){
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < b.length; ++i){
			buffer.append(toHexString1(b[i]));
		}
		return buffer.toString();
	}
	public static String toHexString1(byte b){
		String s = Integer.toHexString(b & 0xFF);
		if (s.length() == 1){
			return "0" + s;
		}else{
			return s;
		}
	}
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password)throws Exception {
		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			kgen.init(128, new SecureRandom(password.getBytes("ASCII")));
			SecretKey secretKey = new SecretKeySpec(password.getBytes("ASCII"),"AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			IvParameterSpec iv = new IvParameterSpec(Constants.VI.getBytes());
//			 Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			Cipher cipher = Cipher.getInstance(ALGORITHMSTR);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key,iv);// 初始化
			return Base64.encodeBase64String(cipher.doFinal(byteContent));
		} catch (Exception e) {
			LOGGER.error("Exception {----}" + e);
			throw new Exception(e);
		}
	}

	/**
	 * 
	 * @param sSrc
	 *            密码串
	 * @param sKey
	 *            秘钥
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String sSrc, String sKey)throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				LOGGER.info("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				LOGGER.info("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
			IvParameterSpec iv = new IvParameterSpec(VI.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用bAES64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				LOGGER.error("Exception {----}" + e);
				throw new Exception(e);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception {----}" + ex);
			throw new Exception(ex);
		}
	}

	/**
	 * aes解密
	 * 
	 * @param encrypt
	 *            内容
	 * @return
	 * @throws Exception
	 */
	public static String aesDecrypt(String encrypt){
		return aesDecrypt(encrypt, KEY);
	}

	/**
	 * aes加密
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String content) throws Exception {
		return aesEncrypt(content, KEY);
	}

	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws IOException
	 */
	public static byte[] base64Decode(String base64Code) throws IOException {
		return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128);
			Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
			return cipher.doFinal(content.getBytes("utf-8"));
		} catch (Exception e) {
			throw new Exception("AES加密异常！", e);
		}
	}

	/**
	 * AES加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}

	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128);
			Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"), iv);
			byte[] decryptBytes = cipher.doFinal(encryptBytes);
			return new String(decryptBytes);
		} catch (Exception e) {
			throw new Exception("AES解密异常！", e);
		}

	}

	/**
	 * 将base 64 code AES解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) {
		try {
			return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
		} catch (Exception e) {
			LOGGER.error("aesDecrypt error!",e);
		}
		return null;
	}
	
	/**
	 * 验证sign信息
	 * 
	 * @param sign
	 * @return [验证结果对错，返回的错误信息，本地加密后的sign，accessToken,appId]
	 */
//	public static String[] checkSign(String sign,ITbAppsSV appsSV) throws Exception {
//		LOGGER.info("checkSign sign is:" + sign);
//		String[] checkS = new String[5];
//		checkS[0] = "false";
//		// 返回Sign为空的结构体
//		if (null == sign || StringUtils.isEmpty(sign)) {
//			checkS[1] = "sign is null";
//		}
//		String base64String = MoBase64.getFromBase64(sign);
//		String[] base64 = base64String.split(",");
//		// 返回参数个数不对的结构体
//		if (null == base64 || base64.length != 5) {
//			checkS[1] = "参数个数不对";
//			return checkS;
//		}
//		String version = base64[0];
//		String appId = base64[1];
//		String token = base64[2];
//		String timestamp = base64[3];
//		String signS = base64(timestamp, appId, token, version,appsSV).replaceAll("\r|\n", "");
//		if (!signS.equals(sign)) {
//			checkS[1] = "不是合法签名";
//			return checkS;
//		}
//		checkS[0] = "true";
//		checkS[1] = "正确";
//		checkS[2] = signS;
//		checkS[3] = token;
//		checkS[4] = appId;
//
//		return checkS;
//	}

	/**
	 * base64 加密
	 * 
	 * @param timestamp
	 * @param appId
	 * @param accessToken
	 * @param version
	 * @return
	 * @throws Exception
	 */
//	public static String base64(String timestamp, String appId, String accessToken, String version,ITbAppsSV appsSV) throws Exception {
//		String sign = sign(timestamp, appId, accessToken, version,appsSV);
//		StringBuilder buf = new StringBuilder("");
//		// 例如：version,appid,access_token,timestamp,sign
//		buf.append(version);
//		buf.append(",").append(appId);
//		buf.append(",").append(accessToken);
//		buf.append(",").append(timestamp);
//		buf.append(",").append(sign);
//        LOGGER.info("一级平台加密："+MoBase64.getBase64(buf.toString()));
//		return MoBase64.getBase64(buf.toString()).replaceAll("\r|\n", "");
//	}
	
	/**
	 * 签名计算
	 * 
	 * @param timestamp
	 * @param appId
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
//	public static String sign(String timestamp, String appId, String accessToken, String version,ITbAppsSV appsSV) throws Exception {
//		// 签名数据
//		String sign = null;
//		String data = timestamp + appId + accessToken + version;
//		String appSecret;
//		TbApps app = appsSV.getByInfo(appId);
//        LOGGER.info("app:>>>>>>"+app);
//		if (app != null) {
//			appSecret = app.getAppCrkey();
//			// MD5WithRSA签名
//			try {
//				sign = new RSAPKCS1Sign(appSecret).sign(data, Constants.CHARSET);
//			} catch (Exception e) {
//				LOGGER.info("sign calculation"+ ExceptionUtils.getFullStackTrace(e));
//				throw new Exception("sign calculation",e.getMessage());
//			}
//		}
//		return sign;
//	}

}
