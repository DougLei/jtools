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
	public Class<?>[] supportClasses() {
		return new Class[] {Timestamp.class};
	}

	@Override
	public Timestamp convert(Object value) throws DataTypeConvertException {
		try {
			return DateFormatUtil.parseSqlTimestamp(value);
		} catch (Exception e) {
			throw new DataTypeConvertException(value, Timestamp.class, e);
		}
	}
}
