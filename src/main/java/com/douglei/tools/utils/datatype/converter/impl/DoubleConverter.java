package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class DoubleConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Double.class, double.class};
	}

	@Override
	public Double doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(VerifyTypeMatchUtil.isDouble(str)) {
			return Double.parseDouble(str);
		}
		throw new ConvertException("将数据值转换为double类型时, 源数据值["+str+"]非double类型");
	}
}
