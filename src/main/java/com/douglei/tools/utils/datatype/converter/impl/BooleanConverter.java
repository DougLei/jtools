package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class BooleanConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Boolean.class, boolean.class};
	}

	@Override
	public Boolean doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(VerifyTypeMatchUtil.isBoolean(str)) {
			return Boolean.parseBoolean(str);
		}
		throw new ConvertException("将数据值转换为boolean类型时, 源数据值["+str+"]非boolean类型");
	}
}