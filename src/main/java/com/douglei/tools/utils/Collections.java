package com.douglei.tools.utils;

import java.util.ArrayList;
import java.util.List;

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
		if(objects.length > 0) {
			List<Object> list = new ArrayList<Object>(objects.length);
			for (Object object : objects) {
				list.add(object);
			}
			objects = null;
			return list;
		}
		return null;
	}
}
