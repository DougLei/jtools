package com.douglei.utils.serialize;

import org.junit.Test;

import com.douglei.tools.utils.serialize.JdkSerializeProcessor;

/**
 * 
 * @author DougLei
 */
public class JdkSerializeProcessorTest {
	private static final User user = new User("金石磊-DougLei", 27);
	private static final String targetFile = "C:\\serialize\\user.orm";
	
	@Test
	public void serialize() {
		JdkSerializeProcessor.serialize(user, targetFile);
	}
	
	@Test
	public void deserialize() {
		User user = JdkSerializeProcessor.deserialize(User.class, targetFile);
		System.out.println(user.getName());
		System.out.println(user.getAge());
	}
}
