package com.douglei.tools.utils.datatype.converter.impl;

import java.math.BigDecimal;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.Converter;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;

/**
 * 
 * @author DougLei
 */
public class ShortConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Short.class, short.class};
	}

	@Override
	public Short convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == short.class)
			return (short) value;
		if(value instanceof BigDecimal)
			return ((BigDecimal)value).shortValue();
		
		String str = value.toString();
		if(VerifyTypeMatchUtil.isLimitShort(str)) {
			return Short.parseShort(str);
		}
		throw new DataTypeConvertException("将数据值转换为short类型时, 源数据值["+str+"]非short类型, 或长度超出short类型的范围");
	}
}
