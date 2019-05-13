package com.douglei.utils;

/**
 * 字符串工具类
 * @author StoneKing
 */
public class StringUtil {
	
	/**
	 * <pre>
	 * 	字符串是否为空
	 * 	trim()后的空字符串, 也会被判定为空
	 * </pre>
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string){
		if(string == null || "".equals(string.trim())){
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
	 * @param string
	 * @return
	 */
	public static boolean notEmpty(String string){
		return !isEmpty(string);
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
	 * @param string
	 * @return
	 */
	public static String trimUnderline(String string) {
		return trimUnderline_(string)[2];
	}
	
	/**
	 * <pre>
	 * 	去掉前后的下划线
	 * 	[0]=前下划线, 如果没有返回null
	 * 	[1]=后下划线, 如果没有返回null
	 * 	[2]=去掉前后下划线的string
	 * </pre>
	 * @param string
	 * @return
	 */
	public static String[] trimUnderline_(String string) {
		String[] result = new String[3];
		
		int topIndex = 0;
		int bottomIndex = string.length();
		
		while(topIndex < bottomIndex && string.charAt(topIndex) == '_') {
			topIndex++;
		}
		while(topIndex < bottomIndex && string.charAt(bottomIndex-1) == '_') {
			bottomIndex--;
		}
		
		boolean flag = false;
		if(topIndex > 0) {
			result[0] = string.substring(0, topIndex);
			flag = true;
		}
		if(bottomIndex < string.length()) {
			result[1] = string.substring(bottomIndex);
			flag = true;
		}
		result[2] = flag?string.substring(topIndex, bottomIndex):string;
		return result;
	}
}
