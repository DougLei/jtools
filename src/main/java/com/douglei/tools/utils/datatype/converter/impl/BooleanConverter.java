package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class BooleanConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Boolean.class, boolean.class};
	}

	@Override
	public Boolean convert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isBoolean(str)) {
			return Boolean.parseBoolean(str);
		}
		throw new ConvertException("将数据值转换为boolean类型时, 源数据值["+str+"]非boolean类型");
	}
}
