package com.douglei.tools.datatype.dateformat.impl;

import com.douglei.tools.datatype.DataTypeValidateUtil;

/**
 * 
 * @author DougLei
 */
public class LongDateFormat extends AsOfSecondDateFormat {

	@Override
	public boolean isDate(String str) {
		return DataTypeValidateUtil.isLimitLong(str);
	}
}
