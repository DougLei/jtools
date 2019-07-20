package com.douglei.tools.utils.datatype.converter;

/**
 * 转换器
 * @author DougLei
 */
public interface IConverter {
	
	/**
	 * 转换的目标类型
	 * @return
	 */
	Class<?>[] targetClasses();
	
	/**
	 * 转换
	 * @param object
	 * @return
	 * @throws ConvertException
	 */
	Object doConvert(Object object) throws ConvertException;
}
