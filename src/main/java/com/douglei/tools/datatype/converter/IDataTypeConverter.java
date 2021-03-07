package com.douglei.tools.datatype.converter;

/**
 * 转换器
 * @author DougLei
 */
public interface IDataTypeConverter {
	
	/**
	 * 是否是简单的数据类型, 例如: String, Date, Integer...
	 * @return
	 */
	default boolean isSimple() {
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
	 * @param valueClazz value的class类型; 针对转换8个基本类型时使用到该参数
	 * @param targetClazz 要转换的目标类型; 针对转换8个基本类型时使用到该参数
	 * @return
	 */
	Object convert(Object value, Class<?> valueClazz, Class<?> targetClazz);
}
