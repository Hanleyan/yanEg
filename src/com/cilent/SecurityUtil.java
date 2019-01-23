package com.cilent;

import java.util.Random;

import com.util.MD5Util;

public class SecurityUtil {

	private static final int RANDOM_LENGTH = 16;
	
	public static final String USERNAME = "cx580test";
	public static final String PASSWORD = "j6tEF1eyHrdIwNAkCukRRQ==";

	/**
	 * 加密
	 * @param content
	 * @return
	 */
	public static String encrypt(String content) {
		return encryptByPassword(PASSWORD,content);
	}
	
	/**
	 * 加密, 直接传秘钥进行加�?	 * @param password
	 * @param content
	 * @return
	 */
	public static String encryptByPassword(String password, String content) {
		String result = content;
		
		if(password != null){
			try {
				AESCrypt aesCrypt = new AESCrypt(password);
				String randomStr = getRandom(RANDOM_LENGTH);
				String contentStr = randomStr + content; 
				String md5Str = MD5Util.MD5(new String(contentStr.getBytes()));
				String toAesString = contentStr + md5Str;
				result = aesCrypt.encrypt(toAesString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public static String getRandom(int length) {// length 小于或等�?6
		return (new Random().nextLong() + "").substring(0, length);
	}
	
	
	public static void main(String[] argsx ){
		String content = "123";
		System.out.println(SecurityUtil.encrypt(content));
	}
}
