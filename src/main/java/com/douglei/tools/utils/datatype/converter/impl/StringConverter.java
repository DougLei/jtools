package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class StringConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {String.class};
	}

	@Override
	public String doConvert(Object object) throws ConvertException {
		return object.toString();
	}
}
