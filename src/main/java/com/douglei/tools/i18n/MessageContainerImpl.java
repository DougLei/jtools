package com.douglei.tools.i18n;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author DougLei
 */
public class MessageContainerImpl implements MessageContainer {
	protected final Map<String, Map<String, String>> container = new HashMap<String, Map<String,String>>(4);
	
	@Override
	public void putMessage(String language, Set<Entry<Object, Object>> messageKV) {
		Map<String, String> messages = container.get(language);
		if(messages == null) {
			messages = new HashMap<String, String>(64);
			container.put(language, messages);
		}
		for(Entry<Object, Object> kv : messageKV) 
			messages.put(kv.getKey().toString(), kv.getValue().toString());
	}

	@Override
	public Message getMessage(String language, String code, Object... params) {
		Map<String, String> messageMap = container.get(language);
		if(messageMap == null)
			return new Message(null, code, params);
		return new Message(messageMap.get(code), code, params);
	}
}
