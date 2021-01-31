package com.douglei.tools.datatype.converter.impl;

import java.sql.Timestamp;

import com.douglei.tools.datatype.DateFormatUtil;
import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class SqlTimestampDateConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Timestamp.class};
	}

	@Override
	public Timestamp convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		return DateFormatUtil.parseSqlTimestamp(value);
	}
}
