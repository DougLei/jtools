package com.douglei.tools.datatype.converter.impl;

import java.sql.Time;

import com.douglei.tools.datatype.DateFormatUtil;
import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class SqlTimeDateConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Time.class};
	}

	@Override
	public Time convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		return DateFormatUtil.parseSqlTime(value);
	}
}
