package com.douglei.tools.utils.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.reflect.ConstructorUtil;
import com.douglei.tools.utils.reflect.IntrospectorUtil;

/**
 * 数据类型转换工具类
 * @author DougLei
 */
public class ConvertUtil {
	
	/**
	 * 简单的数据类型转换
	 * <pre>
	 * 	8大基本类型
	 *  String类型
	 * </pre>
	 * @param value
	 * @param targetClass
	 * @return
	 */
	public static Object simpleConvert(Object value, Class<?> targetClass) {
		if(value == null) {
			return null;
		}
		if(value.getClass() == targetClass) {
			return value;
		}
		
		String valueString = value.toString();
		if(targetClass == String.class) {
			return valueString;
		}
		if(targetClass == char.class || targetClass == Character.class) {
			return valueString.charAt(0);
		}
		if(targetClass == short.class || targetClass == Short.class) {
			if(ValidationUtil.isLimitShort(valueString)) {
				return Short.parseShort(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为short类型时, 源数据值value非short类型");
		}
		if(targetClass == int.class || targetClass == Integer.class) {
			if(ValidationUtil.isLimitInteger(valueString)) {
				return Integer.parseInt(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为int类型时, 源数据值value非int类型");
		}
		if(targetClass == long.class || targetClass == Long.class) {
			if(ValidationUtil.isLimitLong(valueString)) {
				return Long.parseLong(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为long类型时, 源数据值value非long类型");
		}
		if(targetClass == float.class || targetClass == Float.class) {
			if(ValidationUtil.isDouble(valueString)) {
				return Float.parseFloat(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为float类型时, 源数据值value非float类型");
		}
		if(targetClass == double.class || targetClass == Double.class) {
			if(ValidationUtil.isDouble(valueString)) {
				return Double.parseDouble(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为double类型时, 源数据值value非double类型");
		}
		if(targetClass == boolean.class || targetClass == Boolean.class) {
			if(ValidationUtil.isBoolean(valueString)) {
				return Boolean.parseBoolean(valueString);
			}
			throw new IllegalArgumentException("将数据值转换为boolean类型时, 源数据值value非boolean类型");
		}
		if(targetClass == byte.class || targetClass == Byte.class) {
			if(StringUtil.isEmpty(valueString)) {
				throw new IllegalArgumentException("将数据值转换为byte类型时, 源数据值value为空");
			}
			return Byte.parseByte(valueString.trim());
		}
		throw new IllegalArgumentException("该方法只支持8大基本数据类型和String类型的转换操作");
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
