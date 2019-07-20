package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class CharConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Character.class, char.class};
	}

	@Override
	public Character doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(str.length() == 0) {
			return null;
		}
		return str.charAt(0);
	}
}
