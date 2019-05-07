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
	
	/**
	 * <pre>
	 * 	去掉前后的下划线
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String trimUnderline(String str) {
		return trimUnderline_(str)[2];
	}
	
	/**
	 * <pre>
	 * 	去掉前后的下划线
	 * 	[0]=前下划线, 如果没有返回null
	 * 	[1]=后下划线, 如果没有返回null
	 * 	[2]=去掉前后下划线的string
	 * </pre>
	 * @param str
	 * @return
	 */
	public static String[] trimUnderline_(String str) {
		String[] result = new String[3];
		
		int topIndex = 0;
		int bottomIndex = str.length();
		
		while(topIndex < bottomIndex && str.charAt(topIndex) == '_') {
			topIndex++;
		}
		while(topIndex < bottomIndex && str.charAt(bottomIndex-1) == '_') {
			bottomIndex--;
		}
		
		boolean flag = false;
		if(topIndex > 0) {
			result[0] = str.substring(0, topIndex);
			flag = true;
		}
		if(bottomIndex < str.length()) {
			result[1] = str.substring(bottomIndex);
			flag = true;
		}
		result[2] = flag?str.substring(topIndex, bottomIndex):str;
		return result;
	}
}
