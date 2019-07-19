package com.douglei.tools.utils.datatype.converter;

/**
 * 转换器
 * @author DougLei
 */
public interface IConverter<T> {
	
	/**
	 * 要转换的目标类
	 * @return
	 */
	Class<T> targetClass();
	
	/**
	 * 进行转换
	 * @param object
	 * @return
	 * @throws ConvertException
	 */
	T convert(Object object) throws ConvertException ;
}
