package com.douglei.tools.utils;

import java.util.Base64;

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
	 * @param string
	 * @return
	 */
	public static String encodeMD5(String string){
		return encodeMD5(string, null);
	}
	
	/**
	 * md5加密
	 * @param string
	 * @param salt
	 * @return
	 */
	public static String encodeMD5(String string, String salt){
		if(string == null) {
			logger.debug("进行md5加密的字符串不能为空");
			return null;
		}
		logger.trace("将字符串[{}]进行md5加密, 其盐值为[{}]", string, salt);
		if(salt != null) {
			string += salt;
		}
		String result = DigestUtils.md5Hex(string);
		logger.trace("加密的结果为[{}]", result);
		return result;
	}
	
	/**
	 * 比较MD5值是否一致
	 * @param originMD5Value
	 * @param string
	 * @return
	 */
	public static boolean equalsMD5Value(String originMD5Value, String string) {
		String newMD5Value = encodeMD5(string, null);
		return originMD5Value.equals(newMD5Value);
	}
	
	/**
	 * 比较MD5值是否一致
	 * @param originMD5Value
	 * @param string
	 * @param salt
	 * @return
	 */
	public static boolean equalsMD5Value(String originMD5Value, String string, String salt) {
		String newMD5Value = encodeMD5(string, salt);
		return originMD5Value.equals(newMD5Value);
	}
	
	/**
	 * base64加密
	 * @param string
	 * @return
	 */
	public static String encodeByBASE64(String string) {
		return Base64.getEncoder().encodeToString(string.getBytes());
	}
	
	/**
	 * base64解密
	 * @param string
	 * @return
	 */
	public static String decodeByBASE64(String string) {
		byte[] bt = Base64.getDecoder().decode(string.getBytes());
		return new String(bt);
	}
}
