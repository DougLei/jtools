package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class ShortConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Short.class, short.class};
	}

	@Override
	public Short doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isLimitShort(str)) {
			return Short.parseShort(str);
		}
		throw new ConvertException("将数据值转换为short类型时, 源数据值["+str+"]非short类型, 或长度超出short类型的范围");
	}
}
