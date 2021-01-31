package com.douglei.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author StoneKing
 */
public class StringUtil {
	
	/**
	 * 是否为空, 判断时进行了trim操作
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 是否为空, 判断时进行了trim操作
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj){
		return obj == null || obj.toString().trim().length() == 0;
	}
	
	/**
	 * 是否不为空, 判断时进行了trim操作
	 * @param str
	 * @return
	 */
	public static boolean unEmpty(String str){
		return str != null && str.trim().length() > 0;
	}
	
	/**
	 * 是否不为空, 判断时进行了trim操作
	 * @param obj
	 * @return
	 */
	public static boolean unEmpty(Object obj){
		return obj != null && obj.toString().trim().length() > 0;
	}
	
	/**
	 * 去掉前后指定的字符
	 * @param str
	 * @param c
	 * @return
	 */
	public static String trim(String str, char c) {
		int topIndex = 0;
		int bottomIndex = str.length();
		
		while(topIndex < bottomIndex && str.charAt(topIndex) == c) 
			topIndex++;
		
		while(topIndex < bottomIndex && str.charAt(bottomIndex-1) == c) 
			bottomIndex--;
		
		return (topIndex > 0 || bottomIndex < str.length())?str.substring(topIndex, bottomIndex):str;
	}
	
	/**
	 * 计算字符串的长度; 如果存在n个双字节的字符, 长度加n*1
	 * @param str
	 * @return
	 */
	public static int calcLength(String str) {
		if(str == null)
			return 0;
		
		int length = str.length();
		Matcher matcher = pattern.matcher(str);
		while(matcher.find())
			length++;
		return length;
	}
	private static final Pattern pattern = Pattern.compile("[^\\x00-\\xff]"); // 匹配占两个字节的字符的正则表达式
}
