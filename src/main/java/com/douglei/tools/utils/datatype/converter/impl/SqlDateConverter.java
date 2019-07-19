package com.douglei.tools.utils.datatype.converter.impl;

import java.sql.Date;

import com.douglei.tools.utils.datatype.DateTypeUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class SqlDateConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Date.class};
	}

	@Override
	public Date convert(Object object) throws ConvertException {
		try {
			return DateTypeUtil.parseSqlDate(object);
		} catch (Exception e) {
			throw new ConvertException(object, Date.class, e);
		}
	}
}
