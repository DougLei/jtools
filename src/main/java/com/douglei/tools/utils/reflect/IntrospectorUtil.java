package com.douglei.tools.utils.reflect;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.UtilException;
import com.douglei.tools.utils.datatype.converter.ConverterUtil;


/**
 * 内省工具类
 * @author DougLei
 */
public class IntrospectorUtil {
	private static final Logger logger = LoggerFactory.getLogger(IntrospectorUtil.class);
	
	/**
	 * 通过内省, 获取对象指定的属性值, 如果传入的 object是Map, 则直接使用get方法
	 * @param object
	 * @param propertyName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getProperyValue(Object object, String propertyName) {
		if(object instanceof Map) {
			return ((Map)object).get(propertyName);
		}else {
			Class<?> clz = object.getClass();
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(clz);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				
				Method getter = null;
				for (PropertyDescriptor pd : pds) {
					if(pd.getName().equals(propertyName)) {
						getter = pd.getReadMethod();
						if(getter == null) {
							throw new NullPointerException("无法调用 "+clz+"."+toGetMethodName(propertyName)+"方法, 程序没有获取到该方法");
						}
						if(logger.isDebugEnabled()) {
							logger.debug("调用 {}.{}, get出的参数值为 {}, 值类型为 {}", clz, getter.getName(), propertyName, pd.getPropertyType());
						}
						return getter.invoke(object);
					}
				}
			} catch (Exception e) {
				throw new UtilException("["+clz+"]实例, 调用 "+toGetMethodName(propertyName)+"() 方法时, 出现异常", e);
			}
		}
		return null;
	}
	
	/**
	 * 通过内省, 获取对象的属性值集合, 如果传入的 object是Map, 则直接使用get方法
	 * @param object
	 * @param propertyNames
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getProperyValues(Object object, Collection<String> propertyNames) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		
		if(object instanceof Map) {
			Map map = ((Map)object);
			for (String pn : propertyNames) {
				valueMap.put(pn, map.get(pn));
			}
			return valueMap;
		}else {
			Class<?> clz = object.getClass();
			String propertyName = null;
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(clz);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				
				int size = propertyNames.size();
				int index = 0;
				Method getter = null;
				for (PropertyDescriptor pd : pds) {
					propertyName = pd.getName();
					if(propertyNames.contains(propertyName)) {
						getter = pd.getReadMethod();
						if(getter == null) {
							throw new NullPointerException("无法调用 "+clz+"."+toGetMethodName(propertyName)+"方法, 程序没有获取到该方法");
						}
						valueMap.put(propertyName, getter.invoke(object));
						
						if(logger.isDebugEnabled()) {
							logger.debug("调用 {}.{}, get出的参数值为 {}, 值类型为 {}", clz, getter.getName(), valueMap.get(propertyName), pd.getPropertyType());
						}
						index++;
						if(index == size) {
							break;
						}
					}
				}
				return valueMap;
			} catch (Exception e) {
				throw new UtilException("["+clz+"]实例, 调用 "+toGetMethodName(propertyName)+"() 方法时, 出现异常", e);
			}
		}
	}
	private static String toGetMethodName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * 通过内省, 给对象的属性赋值, 如果传入的 object是Map, 则直接使用set方法
	 * @param object
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object setProperyValue(Object object, String propertyName, Object propertyValue) {
		if(propertyValue != null) {
			if(object instanceof Map) {
				((Map)object).put(propertyName, propertyValue);
			}else {
				Class<?> clz = object.getClass();
				try {
					BeanInfo beanInfo = Introspector.getBeanInfo(clz);
					PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
					
					Method setter;
					for (PropertyDescriptor pd : pds) {
						if(pd.getName().equals(propertyName)) {
							setter = pd.getWriteMethod();
							if(setter == null) {
								throw new NullPointerException("无法调用 "+clz+"."+toSetMethodName(propertyName)+"() 方法, 程序没有获取到该方法");
							}
							if(logger.isDebugEnabled()) {
								logger.debug("调用 {}.{}, set进的参数值为 {}, 值类型为 {}", clz, setter.getName(), propertyValue, pd.getPropertyType());
							}
							setter.invoke(object, ConverterUtil.convert(propertyValue, pd.getPropertyType()));
							break;
						}
					}
				} catch (Exception e) {
					throw new UtilException("["+clz+"]实例, 调用 "+toSetMethodName(propertyName)+"() 方法时, 出现异常", e);
				}
			}
		}
		return object;
	}
	
	/**
	 * 通过内省, 给对象的属性赋值, 如果传入的 object是Map, 则直接使用set方法
	 * @param object
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object setProperyValues(Object object, Map<String, ? extends Object> propertyMap) {
		if(propertyMap != null && !propertyMap.isEmpty()) {
			if(object instanceof Map) {
				Map map = ((Map)object);
				for(Entry<String, ? extends Object> pv : propertyMap.entrySet()) {
					if(pv.getValue() != null)
						map.put(pv.getKey(), pv.getValue());
				}
			}else {
				Class<?> clz = object.getClass();
				String propertyName = null;
				try {
					BeanInfo beanInfo = Introspector.getBeanInfo(clz);
					PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
					
					int size = propertyMap.size();
					int index = 0;
					Object value = null;
					Method setter = null;
					for (PropertyDescriptor pd : pds) {
						propertyName = pd.getName();
						if(propertyMap.containsKey(propertyName)) {
							index++;
							
							value = propertyMap.get(propertyName);
							if(value != null) {
								setter = pd.getWriteMethod();
								if(setter == null) {
									throw new NullPointerException("无法调用 "+clz+"."+toSetMethodName(propertyName)+"() 方法, 程序没有获取到该方法");
								}
								setter.invoke(object, ConverterUtil.convert(value, pd.getPropertyType()));
								
								if(logger.isDebugEnabled()) {
									logger.debug("调用 {}.{}, set进的参数值为 {}, 值类型为 {}", clz, setter.getName(), value, pd.getPropertyType());
								}
							}
							if(index == size) {
								break;
							}
						}
					}
				} catch (Exception e) {
					throw new UtilException("["+clz+"]实例, 调用 "+toSetMethodName(propertyName)+"() 方法时, 出现异常", e);
				}
			}
		}
		return object;
	}
	private static String toSetMethodName(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
