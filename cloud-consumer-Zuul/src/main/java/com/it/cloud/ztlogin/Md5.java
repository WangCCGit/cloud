package com.it.cloud.ztlogin;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	public static String getMD5(byte[] source) {
		String s = null;
		try {
			MessageDigest e = MessageDigest.getInstance("MD5");
			e.update(source);
			byte[] tmp = e.digest();
			s = Base64.encodeBase64String(tmp);
		} catch (Exception var4) {
			var4.printStackTrace();
		}
		return s;
	}

	public static String getMD5(String source, String charset) {
		String s = null;
		try {
			MessageDigest e = MessageDigest.getInstance("MD5");
			e.update(source.getBytes(charset));
			byte[] tmp = e.digest();
			s = byteArrayToHexString(tmp);
		} catch (Exception var5) {
			var5.printStackTrace();
		}
		return s;
	}

	public static String getMD5(InputStream in) {
		Object s = null;
		try {
			MessageDigest e = MessageDigest.getInstance("MD5");
			byte[] bytes = new byte[8192];
			int byteCount;
			while ((byteCount = in.read(bytes)) > 0) {
				e.update(bytes, 0, byteCount);
			}
			byte[] digest = e.digest();
			return byteArrayToHexString(digest);
		} catch (NoSuchAlgorithmException var6) {
			var6.printStackTrace();
		} catch (IOException var7) {
			var7.printStackTrace();
		} catch (Exception var8) {
			var8.printStackTrace();
		}
		return (String) s;
	}

	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		byte[] var2 = bytes;
		int var3 = bytes.length;
		for (int var4 = 0; var4 < var3; ++var4) {
			byte b = var2[var4];
			int h = b >> 4 & 15;
			if (h > 9) {
				s.append((char) (h - 10 + 97));
			} else {
				s.append((char) (h + 48));
			}
			h = b & 15;
			if (h > 9) {
				s.append((char) (h - 10 + 97));
			} else {
				s.append((char) (h + 48));
			}
		}
		return s.toString();
	}


	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F" };

	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest(s.getBytes("UTF-8"));
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e) {
			return null;
		}
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5 摘要计算(byte[]).
	 * @param src byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5Digest(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5"); // MD5
																							// is
																							// 16
																							// bit
																							// message
																							// digest

		return alg.digest(src);
	}

	/**
	 * MD5 摘要计算(String).
	 * @param src String
	 * @throws Exception
	 * @return String
	 */
	public static String md5Digest(String src) throws Exception {
		return byteArrayToHexString(md5Digest(src.getBytes("UTF-8")));
	}

	/** Test crypt */
/*	public static void main(String[] args) {
		try {
			// 获得的明文数据
//			String desStr = "MERCHANTID=123456789&ORDERSEQ=20060314000001&ORDERDATE=20060314&ORDERAMOUNT=10000";
//			System.out.println("原文字符串 desStr ＝＝ " + desStr);
//			// 生成MAC
//			String MAC = md5Digest(desStr);
//			System.out.println("MAC == " + MAC);
//
//			// 使用key值生成 SIGN
//			String keyStr = "304F3A86606C585E";// 使用固定key
//			// 获得的明文数据
//			desStr = "UPTRANSEQ=20090327190924&MERCHANTID=3400000001&ORDERID=55120090327843921320&PAYMENT=1&RETNCODE=0000&RETNINFO=0000&PAYDATE=20090327";
//			// 将key值和明文数据组织成一个待签名的串
//			desStr = desStr + "&KEY=" + keyStr;
//			System.out.println("原文字符串 desStr ＝＝ " + desStr);
//			// 生成 SIGN
//			String SIGN = md5Digest(desStr);
//			System.out.println("SIGN == " + SIGN);
			String desStr ="李黎MengXiangTongXing2014";
			String desStr_after = md5Digest(desStr);
			System.out.println("111111加密后：desStr_after:"+desStr_after+" length:"+desStr_after.length());
			if(desStr_after.equalsIgnoreCase("96e79218965eb72c92a549dd5a330112")){
				System.out.println("一致");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/

}
