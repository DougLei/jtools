package com.douglei.tools.i18n;

import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author DougLei
 */
public interface MessageContainer {
	
	/**
	 * 给容器中put指定Language的Messages
	 * @param language
	 * @param messageKV
	 */
	void putMessage(String language, Set<Entry<Object, Object>> messageKV);
	
	/**
	 * 获取message
	 * @param language 
	 * @param code
	 * @param params
	 * @return
	 */
	Message getMessage(String language, String code, Object... params);
}
