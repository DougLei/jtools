package com.douglei.tools.utils.datatype.converter;

/**
 * 转换器
 * @author DougLei
 */
public interface Converter {
	
	/**
	 * 是否是简单的数据类型
	 * 即对象中直接包裹着值, 比如String, Date, Integer...
	 * 默认是简单类型
	 * @return
	 */
	default boolean isSimpleType() {
		return true;
	}
	
	/**
	 * 转换的目标类型
	 * @return
	 */
	Class<?>[] targetClasses();
	
	/**
	 * 转换
	 * @param object
	 * @return
	 * @throws DataTypeConvertException
	 */
	Object doConvert(Object object) throws DataTypeConvertException;
}
