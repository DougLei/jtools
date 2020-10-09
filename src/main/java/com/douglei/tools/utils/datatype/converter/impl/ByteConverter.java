package com.douglei.tools.utils.datatype.converter.impl;

import java.math.BigDecimal;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class ByteConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Byte.class, byte.class};
	}

	@Override
	public Byte convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == byte.class)
			return (byte)value;
		if(value instanceof BigDecimal)
			return ((BigDecimal)value).byteValue();
			
		String str = value.toString();
		if(VerifyTypeMatchUtil.isLimitByte(str)) 
			return Byte.parseByte(str);
		throw new DataTypeConvertException("将数据值转换为byte类型时, 源数据值["+str+"]非byte类型, 或长度超出byte类型的范围");
	}
}
