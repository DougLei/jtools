package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class FloatConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Float.class, float.class};
	}

	@Override
	public Float convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == float.class)
			return (float) value;
		
		String str = value.toString();
		if(VerifyTypeMatchUtil.isDouble(str)) {
			return Float.parseFloat(str);
		}
		throw new DataTypeConvertException("将数据值转换为float类型时, 源数据值["+str+"]非float类型");
	}
}
