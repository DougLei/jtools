package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.converter.DataTypeConvertException;
import com.douglei.tools.utils.datatype.converter.Converter;

/**
 * 
 * @author DougLei
 */
public class IntConverter implements Converter {

	@Override
	public Class<?>[] supportClasses() {
		return new Class[] {Integer.class, int.class};
	}

	@Override
	public Integer convert(Object value) throws DataTypeConvertException {
		if(value.getClass() == int.class)
			return (int) value;
		
		String str = value.toString();
		if(VerifyTypeMatchUtil.isLimitInteger(str)) {
			return Integer.parseInt(str);
		}
		throw new DataTypeConvertException("将数据值转换为int类型时, 源数据值["+str+"]非int类型, 或长度超出int类型的范围");
	}
}
