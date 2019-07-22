package com.douglei.tools.utils.datatype.date.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;

/**
 * 
 * @author DougLei
 */
public class LongDateFormat extends SimpleDetailDateFormat {

	@Override
	protected boolean match(String dateString) {
		return VerifyTypeMatchUtil.isLimitLong(dateString);
	}
}
