package com.douglei.tools.utils.datatype.date.impl;

import java.util.regex.Pattern;

import com.douglei.tools.utils.datatype.date.DateFormat;

/**
 * 
 * @author DougLei
 */
public class UTCDateFormat extends DateFormat {
	private static final Pattern pattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])T([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|0[0-9]|[0-5][0-9]):([0-9]|0[0-9]|[0-5][0-9]).[0-9][0-9][0-9]( )?Z");
	
	@Override
	protected boolean match(String dateString) {
		return pattern.matcher(dateString).matches();
	}

	@Override
	protected String dateFormatPattern() {
		return "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
	}

	@Override
	protected String processDateString(String dateString) {
		return dateString.replace("Z", " UTC");
	}
	
//	public static void main(String[] args) {
//		System.out.println(pattern.matcher("2018-09-08T06:3:35.000Z").matches());
//	}
}
