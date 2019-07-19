package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class LongConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Long.class, long.class};
	}

	@Override
	public Long convert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isLimitLong(str)) {
			return Long.parseLong(str);
		}
		throw new ConvertException("将数据值转换为long类型时, 源数据值["+str+"]非long类型, 或长度超出long类型的范围");
	}
}
