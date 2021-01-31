package com.douglei.tools.datatype;

import java.util.HashMap;
import java.util.Map;

import com.douglei.tools.datatype.converter.IDataTypeConverter;
import com.douglei.tools.reflect.ClassUtil;
import com.douglei.tools.scanner.impl.ClassScanner;

/**
 * 数据类型转换工具类
 * @author DougLei
 */
public class DataTypeConvertUtil {
	static Map<Class<?>, IDataTypeConverter> DATA_TYPE_CONVERTER_MAP = new HashMap<Class<?>, IDataTypeConverter>(8);
	static {
		for (String clazz : new ClassScanner().scan(IDataTypeConverter.class.getPackage().getName() + ".impl")) {
			IDataTypeConverter dataTypeConverter = (IDataTypeConverter)ClassUtil.newInstance(clazz);
			for(Class<?> sc : dataTypeConverter.supportClasses())
				DATA_TYPE_CONVERTER_MAP.put(sc, dataTypeConverter);
		}
	}
	
	/**
	 * 进行类型转换
	 * @param value
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convert(Object value, Class<T> target) {
		if(value == null) 
			return null;
		
		Class<?> clazz = value.getClass();
		if(clazz == target || ClassUtil.isImplementInterface(clazz, target) || ClassUtil.isExtendClass(clazz, target)) 
			return (T) value;
		
		IDataTypeConverter converter = DATA_TYPE_CONVERTER_MAP.get(target);
		if(converter == null)
			throw new IllegalArgumentException("目前不支持["+target.getName()+"]类型的转换");
		return (T) converter.convert(value, clazz, target);
	}
}
