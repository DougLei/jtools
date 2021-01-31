package com.douglei.tools.datatype.dateformat.impl;

import java.util.Date;
import java.util.regex.Pattern;

import com.douglei.tools.datatype.dateformat.AbstractDateFormat;

/**
 * 
 * @author DougLei
 */
public class UTCDateFormat extends AbstractDateFormat {
	private String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
	private Pattern regular = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])T([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|0[0-9]|[0-5][0-9]):([0-9]|0[0-9]|[0-5][0-9]).[0-9][0-9][0-9]( )?Z");
	
	@Override
	public String pattern() {
		return pattern;
	}
	
	@Override
	public boolean isDate(String str) {
		return regular.matcher(str).matches();
	}
	
	@Override
	public Date parse(String str) {
		return super.parse(str.replace("Z", "UTC"));
	}

//	public static void main(String[] args) throws Exception {
//		System.out.println(pattern.matcher("2018-09-08T06:3:35.000Z").matches());
//	}
}
