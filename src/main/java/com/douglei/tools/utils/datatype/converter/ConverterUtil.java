package com.douglei.tools.utils.datatype.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douglei.tools.instances.scanner.ClassScanner;
import com.douglei.tools.utils.reflect.ConstructorUtil;
import com.douglei.tools.utils.reflect.IntrospectorUtil;

/**
 * 数据类型转换器
 * @author DougLei
 */
public class ConverterUtil {
	private static final Map<Class<?>, IConverter<?>> converters = new HashMap<Class<?>, IConverter<?>>();
	static {
		ClassScanner cs = new ClassScanner();
		List<String> classes = cs.scan(ConverterUtil.class.getPackage().getName() + ".impl");
		for (String clz : classes) {
			register((IConverter<?>)ConstructorUtil.newInstance(clz));
		}
	}
	public static void register(IConverter<?> converter) {
		converters.put(converter.targetClass(), converter);
	}
	
	/**
	 * 进行类型转换
	 * @param value
	 * @param targetClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convert(Object value, Class<T> targetClass) {
		if(value == null) {
			return null;
		}
		IConverter<?> converter = converters.get(targetClass);
		if(converter != null) {
			return (T) converter.convert(value);
		}
		throw new UnsupportDataTypeConvertException(value, targetClass);
	}
	
	
	/**
	 * 将map转换为class对象
	 * @param map
	 * @param targetClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToClass(Map<String, Object> map, Class<T> targetClass) {
		if(map != null && map.size() > 0) {
			return (T) IntrospectorUtil.setProperyValues(ConstructorUtil.newInstance(targetClass), map);
		}
		return null;
	}
	
	/**
	 * 将list map转换为list class对象集合
	 * @param listMap
	 * @param targetClass
	 * @return
	 */
	public static <T> List<T> listMapToListClass(List<Map<String, Object>> listMap, Class<T> targetClass){
		if(listMap!= null && listMap.size()>0) {
			List<T> listT = new ArrayList<T>(listMap.size());
			for (Map<String, Object> map : listMap) {
				listT.add(mapToClass(map, targetClass));
			}
			return listT;
		}
		return null;
	}
	
//	/**
//	 * 简单的数据类型转换
//	 * <pre>
//	 * 	8大基本类型
//	 *  String类型
//	 * </pre>
//	 * @param value
//	 * @param targetClass
//	 * @return
//	 */
//	public static Object simpleConvert(Object value, Class<?> targetClass) {
//		if(value == null) {
//			return null;
//		}
//		if(value.getClass() == targetClass) {
//			return value;
//		}
//		
//		String valueString = value.toString();
//		if(targetClass == String.class) {
//			return valueString;
//		}
//		if(targetClass == char.class || targetClass == Character.class) {
//			return valueString.charAt(0);
//		}
//		if(targetClass == short.class || targetClass == Short.class) {
//			if(ValidationUtil.isLimitShort(valueString)) {
//				return Short.parseShort(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为short类型时, 源数据值value非short类型");
//		}
//		if(targetClass == int.class || targetClass == Integer.class) {
//			if(ValidationUtil.isLimitInteger(valueString)) {
//				return Integer.parseInt(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为int类型时, 源数据值value非int类型");
//		}
//		if(targetClass == long.class || targetClass == Long.class) {
//			if(ValidationUtil.isLimitLong(valueString)) {
//				return Long.parseLong(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为long类型时, 源数据值value非long类型");
//		}
//		if(targetClass == float.class || targetClass == Float.class) {
//			if(ValidationUtil.isDouble(valueString)) {
//				return Float.parseFloat(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为float类型时, 源数据值value非float类型");
//		}
//		if(targetClass == double.class || targetClass == Double.class) {
//			if(ValidationUtil.isDouble(valueString)) {
//				return Double.parseDouble(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为double类型时, 源数据值value非double类型");
//		}
//		if(targetClass == boolean.class || targetClass == Boolean.class) {
//			if(ValidationUtil.isBoolean(valueString)) {
//				return Boolean.parseBoolean(valueString);
//			}
//			throw new IllegalArgumentException("将数据值转换为boolean类型时, 源数据值value非boolean类型");
//		}
//		if(targetClass == byte.class || targetClass == Byte.class) {
//			if(StringUtil.isEmpty(valueString)) {
//				throw new IllegalArgumentException("将数据值转换为byte类型时, 源数据值value为空");
//			}
//			return Byte.parseByte(valueString.trim());
//		}
//		throw new IllegalArgumentException("该方法只支持8大基本数据类型和String类型的转换操作");
//	}
}
