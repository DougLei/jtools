package com.douglei.tools.utils.serialize;

import java.io.File;
import java.io.Serializable;

import com.douglei.tools.utils.serialize.exception.NotSerializableException;
import com.douglei.tools.utils.serialize.exception.SerializeFileNotFoundException;


/**
 * 序列化处理器
 * @author DougLei
 */
abstract class SerializeProcessor {
	
	/**
	 * 是否可序列化
	 * @param object
	 */
	protected static void isSerializable(Object object) {
		if(!(object instanceof Serializable)) {
			throw new NotSerializableException(object);
		}
	}
	
	/**
	 * 是否存在序列化文件
	 * @param serializationFile
	 */
	protected static void serializationFileExists(File serializationFile) {
		if(!serializationFile.exists()) {
			throw new SerializeFileNotFoundException(serializationFile);
		}
	}
}
