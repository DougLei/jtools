package com.douglei.tools.datatype.dateformat.impl;

import java.util.regex.Pattern;

import com.douglei.tools.datatype.dateformat.AbstractDateFormat;

/**
 * 
 * @author DougLei
 */
public class AsOfDayDateFormat extends AbstractDateFormat {
	private Pattern regular = Pattern.compile("[0-9]{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])");
	
	@Override
	public String pattern() {
		return "yyyy-MM-dd";
	}
	
	@Override
	public boolean isDate(String str) {
		return regular.matcher(str).matches();
	}

//	public static void main(String[] args) {
//		System.out.println(regular.matcher("2018-09-08").matches());
//	}
}
