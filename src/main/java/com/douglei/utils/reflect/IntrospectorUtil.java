package com.douglei.utils.reflect;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.utils.datatype.ConvertUtil;


/**
 * 内省工具类
 * @author DougLei
 */
public class IntrospectorUtil {
	private static final Logger logger = LoggerFactory.getLogger(IntrospectorUtil.class);
	
	/**
	 * <pre>
	 * 	通过内省, 获取对象的属性值集合
	 * 	只支持基础数据类型
	 * </pre>
	 * @param introspectorObject
	 * @param propertyNames
	 * @return
	 */
	public static Map<String, Object> getProperyValues(Object introspectorObject, Collection<String> propertyNames) {
		if(introspectorObject == null) {
			logger.debug("getProperyValues时, 传入的introspectorObject实例=null");
			return null;
		}
		Class<?> introspectorClass = introspectorObject.getClass();
		if(logger.isDebugEnabled()) {
			logger.debug("获取[{}]实例, {} 属性值集合", introspectorClass, propertyNames);
		}
		
		if(propertyNames != null && propertyNames.size() > 0) {
			String propertyName = null;
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(introspectorClass);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				
				int size = propertyNames.size();
				Map<String, Object> valueMap = new HashMap<String, Object>(size);
				
				int index = 0;
				Method getter = null;
				for (PropertyDescriptor pd : pds) {
					propertyName = pd.getName();
					if(propertyNames.contains(propertyName)) {
						getter = pd.getReadMethod();
						if(getter == null) {
							throw new NullPointerException("无法调用 "+introspectorClass+"."+toGetMethodName(propertyName)+"方法, 程序没有获取到该方法");
						}
						valueMap.put(propertyName, getter.invoke(introspectorObject));
						
						if(logger.isDebugEnabled()) {
							logger.debug("调用 {}.{}, get出的参数值为 {}, 值类型为 {}", introspectorClass, getter.getName(), valueMap.get(propertyName), pd.getPropertyType());
						}
						index++;
						if(index == size) {
							break;
						}
					}
				}
				return valueMap;
			} catch (Exception e) {
				throw new RuntimeException("["+introspectorClass+"]实例, get "+propertyName+" 属性的值时, 出现异常", e);
			}
		}
		return null;
	}
	private static String toGetMethodName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * <pre>
	 * 	通过内省, 给对象的属性赋值
	 * 	只支持基础数据类型
	 * </pre>
	 * @param introspectorObject
	 * @param propertyMap
	 * @return
	 */
	public static Object setProperyValues(Object introspectorObject, Map<String, ? extends Object> propertyMap) {
		if(introspectorObject == null) {
			logger.debug("setProperyValues时, 传入的introspectorObject实例=null");
			return null;
		}
		Class<?> introspectorClass = introspectorObject.getClass();
		if(logger.isDebugEnabled()) {
			logger.debug("给[{}]实例, set {} 属性值map集合", introspectorClass, propertyMap);
		}
		
		if(propertyMap != null && propertyMap.size() > 0) {
			String propertyName = null;
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(introspectorClass);
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
								throw new NullPointerException("无法调用 "+introspectorClass+"."+toSetMethodName(propertyName)+"方法, 程序没有获取到该方法");
							}
							setter.invoke(introspectorObject, ConvertUtil.simpleConvert(value, pd.getPropertyType()));
							
							if(logger.isDebugEnabled()) {
								logger.debug("调用 {}.{}, set进的参数值为 {}, 值类型为 {}", introspectorClass, setter.getName(), value, pd.getPropertyType());
							}
						}
						if(index == size) {
							break;
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("["+introspectorClass+"]实例, set "+propertyName+" 属性的值时, 出现异常", e);
			}
		}
		return introspectorObject;
	}
	private static String toSetMethodName(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
