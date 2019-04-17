package com.douglei.utils;

/**
 * 字符串工具类
 * @author StoneKing
 */
public class StringUtil {
	
	/**
	 * 字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * object类型的字符串是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		if(object == null || "".equals(object.toString().trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * object类型的字符串是否不为空
	 * @param object
	 * @return
	 */
	public static boolean notEmpty(Object object){
		return !isEmpty(object);
	}
}
