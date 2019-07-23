package com.douglei.tools.utils.naming.converter;

/**
 * 
 * @author DougLei
 */
public interface Converter {
	
	/**
	 * 将指定名称转换为其他name
	 * @param originName
	 * @return
	 */
	String doConvert(String originName);
}
