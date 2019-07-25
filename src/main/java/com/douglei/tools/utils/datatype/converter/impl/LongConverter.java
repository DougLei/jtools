package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class LongConverter implements Converter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Long.class, long.class};
	}

	@Override
	public Long doConvert(Object object) throws DataTypeConvertException {
		String str = object.toString();
		if(VerifyTypeMatchUtil.isLimitLong(str)) {
			return Long.parseLong(str);
		}
		throw new DataTypeConvertException("将数据值转换为long类型时, 源数据值["+str+"]非long类型, 或长度超出long类型的范围");
	}
}
