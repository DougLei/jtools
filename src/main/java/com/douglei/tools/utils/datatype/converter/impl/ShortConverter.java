package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class ShortConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Short.class, short.class};
	}

	@Override
	public Short doConvert(Object object) throws DataTypeConvertException {
		String str = object.toString();
		if(VerifyTypeMatchUtil.isLimitShort(str)) {
			return Short.parseShort(str);
		}
		throw new DataTypeConvertException("将数据值转换为short类型时, 源数据值["+str+"]非short类型, 或长度超出short类型的范围");
	}
}
