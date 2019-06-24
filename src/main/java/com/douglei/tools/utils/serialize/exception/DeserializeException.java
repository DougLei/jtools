package com.douglei.tools.utils.serialize.exception;

import java.io.File;

/**
 * 反序列化异常
 * @author DougLei
 */
public class DeserializeException extends RuntimeException{
	private static final long serialVersionUID = 7581057236105588826L;

	public DeserializeException(Class<?> targetClass, File serializationFile, Throwable t) {
		super("读取文件["+serializationFile.getAbsolutePath()+"], 反序列化对象["+targetClass.getName()+"]时出现异常", t);
	}
}
