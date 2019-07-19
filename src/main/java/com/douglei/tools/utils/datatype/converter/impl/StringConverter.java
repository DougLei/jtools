package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class StringConverter implements IConverter<String> {

	@Override
	public Class<String> targetClass() {
		return String.class;
	}

	@Override
	public String convert(Object object) throws ConvertException {
		// TODO Auto-generated method stub
		return null;
	}
}
