package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class DoubleConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Double.class, double.class};
	}

	@Override
	public Double convert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isDouble(str)) {
			return Double.parseDouble(str);
		}
		throw new ConvertException("将数据值转换为double类型时, 源数据值["+str+"]非double类型");
	}
}
