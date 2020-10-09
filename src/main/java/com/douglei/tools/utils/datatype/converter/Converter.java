package com.douglei.tools.utils.datatype.converter;

/**
 * 转换器
 * @author DougLei
 */
public interface Converter {
	
	/**
	 * 是否是简单的数据类型, 即对象中直接包裹着值, 例如String, Date, Integer...
	 * @return
	 */
	default boolean isSimpleType() {
		return true;
	}
	
	/**
	 * 支持(可以进行转换)的类型
	 * @return
	 */
	Class<?>[] supportClasses();
	
	/**
	 * 转换
	 * @param value
	 * @return
	 * @throws DataTypeConvertException
	 */
	Object convert(Object value) throws DataTypeConvertException;
}
