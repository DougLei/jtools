package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class CharConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Character.class, char.class};
	}

	@Override
	public Character convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == char.class)
			return (char)value;
		
		String str = value.toString();
		if(str.length() == 0) 
			return null;
		return str.charAt(0);
	}
}
