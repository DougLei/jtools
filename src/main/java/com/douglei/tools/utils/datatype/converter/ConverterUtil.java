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
	private static final Map<Class<?>, IConverter> converters = new HashMap<Class<?>, IConverter>();
	static {
		ClassScanner cs = new ClassScanner();
		List<String> classes = cs.scan(ConverterUtil.class.getPackage().getName() + ".impl");
		for (String clz : classes) {
			register((IConverter)ConstructorUtil.newInstance(clz));
		}
	}
	
	/**
	 * 注册类型转换器, 如果有重复, 则用最新的替换
	 * @param converter
	 */
	public static void register(IConverter converter) {
		for(Class<?> clz: converter.targetClasses()) {
			converters.put(clz, converter);
		}
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
		if(value.getClass() == targetClass) {
			return (T) value;
		}
		
		IConverter converter = converters.get(targetClass);
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
}
