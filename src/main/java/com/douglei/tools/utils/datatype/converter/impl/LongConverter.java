package com.douglei.tools.utils.datatype.converter.impl;

import java.math.BigDecimal;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class LongConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Long.class, long.class};
	}

	@Override
	public Long convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == long.class)
			return (long) value;
		if(value instanceof BigDecimal)
			return ((BigDecimal)value).longValue();
		
		String str = value.toString();
		if(VerifyTypeMatchUtil.isLimitLong(str)) {
			return Long.parseLong(str);
		}
		throw new DataTypeConvertException("将数据值转换为long类型时, 源数据值["+str+"]非long类型, 或长度超出long类型的范围");
	}
}
