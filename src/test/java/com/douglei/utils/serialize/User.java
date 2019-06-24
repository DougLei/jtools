package com.douglei.utils.serialize;

import java.io.Serializable;

public class User 
	implements Serializable{
	private static final long serialVersionUID = 6764133372781316167L;
	
	private String name;
	private int age;

	public int getAge() {
		return age;
	}
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
