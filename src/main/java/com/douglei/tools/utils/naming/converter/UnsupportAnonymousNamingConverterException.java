package com.douglei.tools.utils.naming.converter;

/**
 * 不支持匿名的命名转换器异常
 * @author DougLei
 */
public class UnsupportAnonymousNamingConverterException extends RuntimeException{
	private static final long serialVersionUID = 358413316168869193L;

	public UnsupportAnonymousNamingConverterException() {
		super("命名转换器["+ConverterUtil.class.getName()+"]存在缓存处理, 所以不支持匿名的命名转换器");
	}
}
