package com.douglei.tools.utils.datatype.dateformat.impl;

import java.util.regex.Pattern;

import com.douglei.tools.utils.datatype.dateformat.DateFormat;

/**
 * 
 * @author DougLei
 */
public class SimpleDetailDateFormat extends DateFormat {
	private static final Pattern pattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1]) ([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|0[0-9]|[0-5][0-9]):([0-9]|0[0-9]|[0-5][0-9])");
	
	@Override
	protected boolean match(String dateString) {
		return pattern.matcher(dateString).matches();
	}

	@Override
	protected String dateFormatPattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
//	public static void main(String[] args) {
//		System.out.println(pattern.matcher("2018-09-8 01:10:01").matches());
//	}
}
