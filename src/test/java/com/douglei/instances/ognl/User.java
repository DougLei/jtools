package com.douglei.instances.ognl;

public class User {
	private String name;
	private String sex;
	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public static User getInstance() {
		User user = new User();
		user.setName("金石磊");
		user.setSex("男");
		
		Address address = new Address();
		address.setAddress("陕西省西安市");
		user.setAddress(address);
		return user;
	}
	public static User getOtherInstance() {
		User user = new User();
		user.setName("成荣荣");
		user.setSex("女");
		
		Address address = new Address();
		user.setAddress(address);
		return user;
	}
}
