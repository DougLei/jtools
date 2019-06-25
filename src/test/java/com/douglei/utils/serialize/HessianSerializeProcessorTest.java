package com.douglei.utils.serialize;

import org.junit.Test;

import com.douglei.tools.utils.serialize.HessianSerializeProcessor;

public class HessianSerializeProcessorTest {
	private static final User user = new User("金石磊-DougLei", 27);
	private static final String targetFile = "C:\\serialize\\user.orm";
	
	@Test
	public void serialize() {
		HessianSerializeProcessor.serialize2File(user, targetFile);
	}
	
	@Test
	public void deserialize() {
		User user = HessianSerializeProcessor.deserializeFromFile(User.class, targetFile);
		System.out.println(user.getName());
		System.out.println(user.getAge());
	}
	
	@Test
	public void serializeByte() {
		byte[] b = HessianSerializeProcessor.serialize2ByteArray(user);
		
		User user = HessianSerializeProcessor.deserializeFromByteArray(User.class, b);
		System.out.println(user.getName());
		System.out.println(user.getAge());
//		System.out.println(user);
//		System.out.println(this.user);
	}
}
