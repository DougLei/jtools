package com.douglei.tools.utils.datatype.converter.impl;

import java.util.Date;

import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;
import com.douglei.tools.utils.datatype.dateformat.DateFormatUtil;

/**
 * 
 * @author DougLei
 */
public class DateConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Date.class};
	}

	@Override
	public Date doConvert(Object object) throws DataTypeConvertException {
		try {
			return DateFormatUtil.parseDate(object);
		} catch (Exception e) {
			throw new DataTypeConvertException(object, Date.class, e);
		}
	}
}
