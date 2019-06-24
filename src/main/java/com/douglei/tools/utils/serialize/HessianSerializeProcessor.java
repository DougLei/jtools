package com.douglei.tools.utils.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.FileUtil;
import com.douglei.tools.utils.serialize.exception.DeserializeException;
import com.douglei.tools.utils.serialize.exception.SerializableException;

/**
 * hessian序列化处理器
 * @author DougLei
 */
public class HessianSerializeProcessor extends SerializeProcessor{
	
	/**
	 * 序列化到文件
	 * @param object
	 * @param targetFile 目标文件的路径
	 * @return
	 */
	public static boolean serialize(Object object, String targetFile) {
		isSerializable(object);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(baos);
		try {
			ho.writeObject(object);
			writeBytes2File(baos, object, FileUtil.getFile(targetFile));
			return true;
		} catch (IOException e) {
			throw new SerializableException(object, e);
		}
	}
	
	// 将byte写到文件中
	private static void writeBytes2File(ByteArrayOutputStream baos, Object object, File targetFile) {
		FileOutputStream fis = null;
		try {
			fis = new FileOutputStream(targetFile);
			fis.write(baos.toByteArray());
		} catch (Exception e) {
			throw new SerializableException(object, e);
		} finally {
			CloseUtil.closeIO(fis, baos);
		}
	}

	/**
	 * 根据指定的文件路径, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param serializationFile
	 * @return
	 */
	public static <T> T deserialize(Class<T> targetClass, String serializationFile) {
		return deserialize(targetClass, new File(serializationFile));
	}

	/**
	 * 根据指定的文件, 反序列化
	 * @param targetClass 要转换的目标类
	 * @param serializationFile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(Class<T> targetClass, File serializationFile) {
		serializationFileExists(serializationFile);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(readBytes2File(serializationFile, targetClass));
		HessianInput hi = new HessianInput(bais);
		try {
			return (T) hi.readObject();
		} catch (IOException e) {
			throw new DeserializeException(targetClass, serializationFile, e);
		} finally {
			CloseUtil.closeIO(bais);
			if(hi != null) {
				hi.close();
			}
		}
	}

	// 将文件中的内容读取成byte
	private static byte[] readBytes2File(File serializationFile, Class<?> targetClass) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(serializationFile);
			baos = new ByteArrayOutputStream();
			
			int length = 0;
			byte[] b = new byte[1024];
			while((length = fis.read(b)) != -1) {
				baos.write(b, 0, length);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			throw new DeserializeException(targetClass, serializationFile, e);
		} finally {
			CloseUtil.closeIO(fis, baos);
		}
	}
}
