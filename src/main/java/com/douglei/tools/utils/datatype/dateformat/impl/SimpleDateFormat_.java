package com.douglei.tools.utils.datatype.dateformat.impl;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.douglei.tools.utils.datatype.dateformat.DateFormat;

/**
 * 
 * @author DougLei
 */
public class SimpleDateFormat_ extends DateFormat {
	private Pattern pattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])");
	private String formatPattern = "yyyy-MM-dd";
	private SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
	
	@Override
	protected boolean match(String dateString) {
		return pattern.matcher(dateString).matches();
	}

	@Override
	protected String formatPattern() {
		return formatPattern;
	}

	@Override
	protected SimpleDateFormat sdf() {
		return sdf;
	}

	
//	public static void main(String[] args) {
//		System.out.println(pattern.matcher("2018-09-8").matches());
//	}
}
