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
			logger.debug("进行md5加密的字符串不能为空");
			return null;
		}
		logger.debug("将字符串[{}]进行md5加密, 其盐值为[{}]", str, salt);
		if(salt != null) {
			str += salt;
		}
		String result = DigestUtils.md5Hex(str);
		logger.debug("加密的结果为[{}]", result);
		return result;
	}
	
	// ----------------------------------------------------------------------------------------------
	/**
	 * BASE64加密
	 * @param str
	 * @return
	 */
	public static String encodeBASE64(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}
	
	/**
	 * BASE64解密
	 * @param str
	 * @return
	 */
	public static String decodeBASE64(String str) {
		return new String(Base64.getDecoder().decode(str.getBytes()));
	}
	
	// ----------------------------------------------------------------------------------------------
	private static final byte[] radixes = {'+','.','0','7',':','F','x','=','t','?'};
	private static final byte[] origin = {'!','"','#','$','%','&','\'','(',')','*','+',',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','[','\\',']','^','_','`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','|','}','~'};
	private static final byte[] odd = {'g','I','p','P','7','Q','S','&','e','@','s','3','O','R','x','u','Y','t','m','A','`','r','0','H','*','c',')','a','-','D','9','"','1','\'','+','l','k','X',']','\\','^','8','!','5','v','J','%','d',',','q','E','y','N',':','M','/','z','V','2','G','[','(','$','h','_','U','j','W','b','?','>','4','{','}','L','Z','o',';','#','f','6','<','|','=','T','F','B','C','K','w','.','~','n','i'};
	private static final byte[] even = {'&','A','V','o','1','U','"','.','s',';','O',')','r','H',']','M','S','[','6','-','c',':','w','I','i','(','`',',','9','8','5','X','?','K','v','g','}','7','m','B','~','k','G','0','N','n','j','3','t','D','P','h','Y','L','Z','Q','a','R','4','>','<','W','p','{','%','/','_','=','+','f','\'','u','T','J','y','$','q','|','\\','b','@','E','d','F','#','*','e','!','^','2','x','z','l','C'};
	
	/**
	 * 基于base64加密
	 * @param str
	 * @return
	 */
	public static String encodeWithBASE64(String str) {
		if(str == null) {
			logger.debug("进行加密的字符串不能为空");
			return null;
		}
		byte radix = (byte) (System.currentTimeMillis()%10);
		boolean isOdd = radix%2==1;// 是否是奇数
		StringBuilder sb = new StringBuilder(str.length()+1);
		for(int i=0;i<str.length();i++) {
			sb.append(getEncryptChar(str.charAt(i), isOdd));
		}
		return Base64.getEncoder().encodeToString(sb.append(((char)radixes[radix])).toString().getBytes());
	}
	
	// 获取加密后的char值
	private static char getEncryptChar(char charValue, boolean isOdd) {
		if(charValue > 32 && charValue < 127) {
			for(byte i=0;i<origin.length;i++) {
				if(origin[i] == charValue) {
					return (char)(isOdd?odd[i]:even[i]);
				}
			}
		}
		return charValue;
	}

	/**
	 * 基于base64解密
	 * @param str
	 * @return
	 */
	public static String decodeWithBASE64(String str) {
		if(str == null) {
			logger.debug("进行解密的字符串不能为空");
			return null;
		}
		str = new String(Base64.getDecoder().decode(str.getBytes()));
		int length = str.length()-1;
		boolean isOdd = isOdd(str.charAt(length));
		StringBuilder sb = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			sb.append(getDecryptChar(str.charAt(i), isOdd));
		}
		return sb.toString();
	}
	
	// 判断最后一位char值是否是奇数
	private static boolean isOdd(char lastCharValue) {
		for (byte i = 0; i < radixes.length; i++) {
			if(lastCharValue == radixes[i]) {
				return i%2 == 1;
			}
		}
		return false;
	}

	// 获取解密后的char值
	private static char getDecryptChar(char charValue, boolean isOdd) {
		if(charValue > 32 && charValue < 127) {
			if(isOdd) {
				for(byte i=0;i<odd.length;i++) {
					if(odd[i] == charValue) {
						return (char)(origin[i]);
					}
				}
			}else {
				for(byte i=0;i<even.length;i++) {
					if(even[i] == charValue) {
						return (char)(origin[i]);
					}
				}
			}
		}
		return charValue;
	}
}
