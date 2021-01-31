package com.douglei.tools.datatype.dateformat.impl;

import java.util.regex.Pattern;

import com.douglei.tools.datatype.dateformat.AbstractDateFormat;

/**
 * 
 * @author DougLei
 */
public class AsOfSecondDateFormat extends AbstractDateFormat {
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private Pattern regular = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1]) ([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|0[0-9]|[0-5][0-9]):([0-9]|0[0-9]|[0-5][0-9])");
	
	@Override
	public String pattern() {
		return pattern;
	}
	
	@Override
	public boolean isDate(String str) {
		return regular.matcher(str).matches();
	}
	
//	public static void main(String[] args) {
//		System.out.println(pattern.matcher("2018-09-8 01:10:01").matches());
//	}
}
