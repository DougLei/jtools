package com.douglei.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 密码工具类
 * @author DougLei
 */
public class CryptographyUtil {
	private static final Logger logger = LoggerFactory.getLogger(CryptographyUtil.class);
	
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String encodeMD5(String str){
		return encodeMD5(str, null);
	}
	
	/**
	 * md5加密
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String encodeMD5(String str, String salt){
		if(str == null) {
			throw new NullPointerException("进行md5加密的字符串不能为空");
		}
		logger.trace("将字符串[{}]进行md5加密, 其盐值为[{}]", str, salt);
		if(salt != null) {
			str += salt;
		}
		String result = DigestUtils.md5Hex(str);
		logger.trace("加密的结果为[{}]", result);
		return result;
	}
	
	/**
	 * 比较MD5值是否一致
	 * @param originMD5Value
	 * @param str
	 * @param salt
	 * @return
	 */
	public static boolean equalsMD5Value(String originMD5Value, String str, String salt) {
		String newMD5Value = encodeMD5(str, salt);
		return originMD5Value.equals(newMD5Value);
	}
}
