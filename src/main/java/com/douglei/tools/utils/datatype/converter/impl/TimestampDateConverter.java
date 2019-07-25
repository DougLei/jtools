package com.douglei.tools.utils.datatype.converter.impl;

import java.sql.Timestamp;

import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;
import com.douglei.tools.utils.datatype.dateformat.DateFormatUtil;

/**
 * 
 * @author DougLei
 */
public class TimestampDateConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Timestamp.class};
	}

	@Override
	public Timestamp doConvert(Object object) throws DataTypeConvertException {
		try {
			return DateFormatUtil.parseSqlTimestamp(object);
		} catch (Exception e) {
			throw new DataTypeConvertException(object, Timestamp.class, e);
		}
	}
}
