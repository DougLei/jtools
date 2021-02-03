package com.douglei.tools.i18n;

import java.util.Arrays;

/**
 * 
 * @author DougLei
 */
public class Message {
	private String message;
	private String code;
	private Object[] params;
	
	public Message(String message, String code, Object... params) {
		if(message == null) {
			this.code = code;
			this.params = params;
		}else {
			this.message = params.length>0?String.format(message, params):message;
		}
	}
	
	/**
	 * 获取国际化消息
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 获取国际化编码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 获取国际化编码需要的参数集合
	 * @return
	 */
	public Object[] getParams() {
		return params;
	}

	@Override
	public String toString() {
		if(message == null)
			return "Message [code=" + code + ", params=" + Arrays.toString(params) + "]";
		return "Message [message=" + message + "]";
	}
}
