package com.douglei.tools.utils.datatype.converter.impl;

import com.douglei.tools.utils.datatype.ValidationUtil;
import com.douglei.tools.utils.datatype.converter.ConvertException;
import com.douglei.tools.utils.datatype.converter.IConverter;

/**
 * 
 * @author DougLei
 */
public class IntConverter implements IConverter {

	@Override
	public Class<?>[] targetClasses() {
		return new Class[] {Integer.class, int.class};
	}

	@Override
	public Integer doConvert(Object object) throws ConvertException {
		String str = object.toString();
		if(ValidationUtil.isLimitInteger(str)) {
			return Integer.parseInt(str);
		}
		throw new ConvertException("将数据值转换为int类型时, 源数据值["+str+"]非int类型, 或长度超出int类型的范围");
	}
}
