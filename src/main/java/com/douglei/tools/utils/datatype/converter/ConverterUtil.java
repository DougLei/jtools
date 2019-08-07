package com.douglei.tools.utils.datatype.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douglei.tools.instances.reader.ResourcesReader;
import com.douglei.tools.instances.scanner.ClassScanner;
import com.douglei.tools.utils.reflect.ConstructorUtil;
import com.douglei.tools.utils.reflect.IntrospectorUtil;

/**
 * 数据类型转换器
 * @author DougLei
 */
public class ConverterUtil {
	private static final Map<Class<?>, Converter> CONVERTERS = new HashMap<Class<?>, Converter>(16);
	static {
		ClassScanner cs = new ClassScanner();
		List<String> classes = cs.scan(ConverterUtil.class.getPackage().getName() + ".impl");
		for (String clz : classes) {
			register((Converter)ConstructorUtil.newInstance(clz));
		}
		loadConverterFactories();
	}
	
	/**
	 * 加载datatype.converter.factories配置文件, 读取其中配置内容, register里面配置的转换器
	 * 
	 * 如果提供的转换器不满足使用, 则可以在项目的根目录下添加datatype.converter.factories文件, 里面配置自定义的转换器, 配置的格式为每行一个转换器类全路径
	 * 自定义的转换器需要实现 {@link Converter} 接口
	 */
	private static void loadConverterFactories() {
		ResourcesReader reader = new ResourcesReader("datatype.converter.factories");
		while(reader.ready()) {
			register((Converter)ConstructorUtil.newInstance(reader.readLine()));
		}
	}
	
	/**
	 * 注册类型转换器, 如果有重复, 则用最新的替换
	 * @param converter
	 */
	private static void register(Converter converter) {
		for(Class<?> clz: converter.targetClasses()) {
			CONVERTERS.put(clz, converter);
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
		
		Converter converter = CONVERTERS.get(targetClass);
		if(converter != null) {
			return (T) converter.doConvert(value);
		}
		throw new UnsupportDataTypeConvertException(value, targetClass);
	}
	
	
	/**
	 * 是否是简单的数据类型
	 * 即对象中直接包裹着值, 比如String, Date, Integer...
	 * @param value
	 * @return
	 */
	public static boolean isSimpleType(Object value) {
		if(value != null) {
			return CONVERTERS.get(value.getClass()).isSimpleType();
		}
		return false;
	}
	
	/**
	 * 将map转换为class对象
	 * 前提是map的key, 要和class的属性名一致
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
	 * 前提是map的key, 要和class的属性名一致
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
