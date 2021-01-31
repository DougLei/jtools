package com.douglei.tools.datatype.converter.impl;

import java.sql.Date;

import com.douglei.tools.datatype.DateFormatUtil;
import com.douglei.tools.datatype.converter.IDataTypeConverter;

/**
 * 
 * @author DougLei
 */
public class SqlDateConverter implements IDataTypeConverter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Date.class};
	}

	@Override
	public Date convert(Object value, Class<?> valueClazz, Class<?> targetClazz) {
		return DateFormatUtil.parseSqlDate(value);
	}
}
