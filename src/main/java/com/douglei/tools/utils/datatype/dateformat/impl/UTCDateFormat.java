package com.douglei.tools.utils.datatype.dateformat.impl;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.douglei.tools.utils.datatype.dateformat.DateFormat;

/**
 * 
 * @author DougLei
 */
public class UTCDateFormat extends DateFormat {
	private Pattern pattern = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])T([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|0[0-9]|[0-5][0-9]):([0-9]|0[0-9]|[0-5][0-9]).[0-9][0-9][0-9]( )?Z");
	private String formatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
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
	protected String processDateString(String dateString) {
		return dateString.replace("Z", " UTC");
	}

	@Override
	protected SimpleDateFormat sdf() {
		return sdf;
	}
	
//	public static void main(String[] args) {
//		System.out.println(pattern.matcher("2018-09-08T06:3:35.000Z").matches());
//	}
}
