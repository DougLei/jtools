package com.douglei.tools.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 集合操作工具类
 * @author DougLei
 */
public class Collections {
	
	/**
	 * 将数组转换为 {@link List}
	 * @param objects
	 * @return
	 */
	public static List<Object> toList(Object...objects){
		return toList(Object.class, objects);
	}
	
	/**
	 * 将数组转换为 {@link List}
	 * @param targetClass
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(Class<T> targetClass, T...ts){
		if(ts.length > 0) {
			List<T> list = new ArrayList<T>(ts.length);
			for (T t : ts) {
				list.add(t);
			}
			ts = null;
			return list;
		}
		return null;
	}
	
	/**
	 * 集合是否为空
	 * @param collection
	 * @return
	 */
	public static <E> boolean isEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}
	

	/**
	 * 集合是否不为空
	 * @param collection
	 * @return
	 */
	public static <E> boolean unEmpty(Collection<E> collection) {
		return collection != null && collection.size() > 0;
	}
	
	/**
	 * 集合是否为空
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}
	
	/**
	 * 集合是否不为空
	 * @param map
	 * @return
	 */
	public static <K, V> boolean unEmpty(Map<K, V> map) {
		return map != null && map.size() > 0;
	}
	
	/**
	 * 清空集合
	 * @param collection
	 */
	public static <E> void clear(Collection<E> collection) {
		if(unEmpty(collection)) {
			collection.clear();
		}
	}
	
	/**
	 * 清空集合
	 * @param map
	 */
	public static <K, V> void clear(Map<K, V> map) {
		if(unEmpty(map)) {
			map.clear();
		}
	}
	
    /**
     * 
     * @return
     */
    public static Object[] emptyArray() {
    	return EMPTY_ARRAY;
    }
    private static final Object[] EMPTY_ARRAY = new Object[0];    
}
