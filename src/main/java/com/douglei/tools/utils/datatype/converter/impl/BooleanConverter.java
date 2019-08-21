package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.Converter;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;

/**
 * 
 * @author DougLei
 */
public class BooleanConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Boolean.class, boolean.class};
	}

	@Override
	public Boolean doConvert(Object object) throws DataTypeConvertException {
		String str = object.toString();
		if(VerifyTypeMatchUtil.isBoolean(str)) {
			return Boolean.parseBoolean(str);
		}
		if(str.equals("1")) {
			return true;
		}
		return false;
	}
}
