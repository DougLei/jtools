package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class ByteConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Byte.class, byte.class};
	}

	@Override
	public Byte convert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isLimitByte(str)) {
			return Byte.parseByte(str);
		}
		throw new ConvertException("将数据值转换为byte类型时, 源数据值["+str+"]非byte类型, 或长度超出byte类型的范围");
	}
}
