package com.douglei.tools.reflect;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.douglei.tools.datatype.DataTypeConvertUtil;

/**
 * 内省工具类
 * @author DougLei
 */
public class IntrospectorUtil {
	
	/**
	 * 通过内省机制, 从对象中获取指定name的属性值
	 * @param name
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getValue(String name, Object object) {
		if(object instanceof Map) 
			return ((Map)object).get(name);
		
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
				if(pd.getName().equals(name)) 
					return pd.getReadMethod().invoke(object);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("通过内省机制, 从对象中获取指定name的属性值时出现异常", e);
		}
	}
	
	/**
	 * 通过内省机制, 从对象中获取多个指定name的属性值集合
	 * @param names
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getValues(Collection<String> names, Object object) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		
		if(object instanceof Map) {
			Map map = ((Map)object);
			names.forEach(pn -> valueMap.put(pn, map.get(pn)));
			return valueMap;
		}
		
		try {
			int leftCount = names.size();
			for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
				if(names.contains(pd.getName())) {
					valueMap.put(pd.getName(), pd.getReadMethod().invoke(object));
					if(--leftCount == 0) 
						break;
				}
			}
			return valueMap;
		} catch (Exception e) {
			throw new RuntimeException("通过内省机制, 从对象中获取多个指定name的属性值集合时出现异常", e);
		}
	}
	
	/**
	 * 通过内省机制, 给对象中指定name的属性赋值
	 * @param name
	 * @param value
	 * @param object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setValue(String name, Object value, Object object) {
		if(value == null)
			return;
		
		if(object instanceof Map) {
			((Map)object).put(name, value);
			return;
		}
			
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
				if(pd.getName().equals(name)) {
					pd.getWriteMethod().invoke(object, DataTypeConvertUtil.convert(value, pd.getPropertyType()));
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("通过内省机制, 给对象中指定name的属性赋值时出现异常", e);
		}
	}
	
	/**
	 * 通过内省机制, 给对象中多个指定name的属性赋值
	 * @param valueMap
	 * @param object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setValues(Map<String, ? extends Object> valueMap, Object object) {
		if(valueMap == null || valueMap.isEmpty())
			return;
		
		if(object instanceof Map) {
			Map map = ((Map)object);
			valueMap.entrySet().forEach(entry -> {
				if(entry.getValue() != null)
					map.put(entry.getKey(), entry.getValue());
			});
			return;
		}
		
		try {
			Object value = null;
			int leftCount = valueMap.size();
			for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
				if(valueMap.containsKey(pd.getName())) {
					value = valueMap.get(pd.getName());
					if(value != null) 
						pd.getWriteMethod().invoke(object, DataTypeConvertUtil.convert(value, pd.getPropertyType()));
					
					if(--leftCount == 0) 
						break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("通过内省机制, 给对象中多个指定name的属性赋值时出现异常", e);
		}
	}
}
