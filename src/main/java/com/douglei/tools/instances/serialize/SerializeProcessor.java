package com.douglei.tools.instances.serialize;

import java.io.File;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

/**
 * 序列化处理器
 * @author DougLei
 */
public interface SerializeProcessor {
	
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	byte[] serialize(Object object);
	
	/**
	 * 序列化到文件
	 * @param object
	 * @param targetFile 目标文件的路径
	 * @return
	 */
	boolean serialize(Object object, String targetFile);
	
	/**
	 * 根据指定的文件路径, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param file
	 * @return
	 */
	<T> T deserialize(Class<T> targetClass, String file);
	
	/**
	 * 根据指定的文件, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param file
	 * @return
	 */
	<T> T deserialize(Class<T> targetClass, File file);
	
	/**
	 * 根据指定的byte数组, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param byteArray
	 * @return
	 */
	<T> T deserialize(Class<T> targetClass, byte[] byteArray);
	
	/**
	 * 根据指定的byte输入流, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param byteInputStream
	 * @return
	 */
	<T> T deserialize(Class<T> targetClass, ByteInputStream byteInputStream);
}
