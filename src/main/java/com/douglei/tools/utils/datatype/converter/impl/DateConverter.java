package com.douglei.tools.utils.datatype.converter.impl;

import java.util.Date;
import com.douglei.tools.utils.datatype.DateTypeUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class DateConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Date.class};
	}

	@Override
	public Date doConvert(Object object) throws ConvertException {
		try {
			return DateTypeUtil.parseDate(object);
		} catch (Exception e) {
			throw new ConvertException(object, Date.class, e);
		}
	}
}
