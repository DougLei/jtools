package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class FloatConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Float.class, float.class};
	}

	@Override
	public Float doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isDouble(str)) {
			return Float.parseFloat(str);
		}
		throw new ConvertException("将数据值转换为float类型时, 源数据值["+str+"]非float类型");
	}
}
