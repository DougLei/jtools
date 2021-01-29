package com.douglei.tools.utils;

/**
 * 正则表达式工具类
 * @author DougLei
 */
public class RegularExpressionUtil {
	private static final char[] regular_expression_keys = {'$', '(', ')', '*', '+', '.', '[', '?', '\\', '^', '{', '|'};
	
	/**
	 * 给字符串中是正则表达式关键字的字符加反斜杠(\)
	 * @param str
	 * @return
	 */
	public static String addBackslash4Key(String str) {
		StringBuilder sb = new StringBuilder(str.length()*2);
		char c;
		for(int i=0;i<str.length();i++) {
			c = str.charAt(i);
			for(char k : regular_expression_keys) {
				if(c == k) {
					sb.append('\\');
					break;
				}
			}
			sb.append(c);
		}
		return sb.length() == str.length() ? str : sb.toString();
	}
	
	/**
	 * 指定字符串, 是否包含正则表达式关键字
	 * @param str
	 * @return
	 */
	public static boolean includeKey(String str) {
		char s;
		for(int i=0;i<str.length();i++) {
			s = str.charAt(i);
			for(char k : regular_expression_keys) {
				if(s == k) 
					return true;
			}
		}
		return false;
	}
	
}
