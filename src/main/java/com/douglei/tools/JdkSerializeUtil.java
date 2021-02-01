package com.douglei.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * jdk提供的序列化工具类
 * @author DougLei
 */
public class JdkSerializeUtil {

	/**
	 * 序列化成byte数组
	 * @param object
	 * @return
	 */
	public static byte[] serialize4ByteArray(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new UtilRuntimeException("序列化成byte数组时出现异常", e);
		}
	}
	/**
	 * 从byte数组反序列化
	 * @param clazz
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize4ByteArray(Class<T> clazz, byte[] bytes) {
		try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))){
			return (T) ois.readObject();
		} catch (Exception e) {
			throw new UtilRuntimeException("从byte数组反序列化时出现异常", e);
		}
	}
	
	/**
	 * 序列化到文件
	 * @param object
	 * @param file
	 */
	public static void serialize4File(Object object, File file) {
		File folder = file.getParentFile();
		if(!folder.exists())
			folder.mkdirs();
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(object);
		} catch (IOException e) {
			throw new UtilRuntimeException("序列化到文件时出现异常", e);
		}
	}
	/**
	 * 从文件反序列化
	 * @param clazz
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize4File(Class<T> clazz, File file) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (T) ois.readObject();
		} catch (Exception e) {
			throw new UtilRuntimeException("从文件反序列化时出现异常", e);
		}
	}
}
