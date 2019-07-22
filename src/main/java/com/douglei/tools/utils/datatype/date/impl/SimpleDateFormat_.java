package com.douglei.tools.utils.datatype.date.impl;

import java.util.regex.Pattern;

import com.douglei.tools.utils.datatype.date.DateFormat;

/**
 * 
 * @author DougLei
 */
public class SimpleDateFormat_ extends DateFormat {
	private static final Pattern pattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])");
	
	
	@Override
	protected boolean match(String dateString) {
		return false;
	}

	@Override
	protected String dateFormatPattern() {
		return "yyyy-MM-dd";
	}
	
	public static void main(String[] args) {
		System.out.println(pattern.matcher("2018-09-8").matches());
	}
}
