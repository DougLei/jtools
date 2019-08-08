package com.douglei.tools.utils.naming.column;

/**
 * 
 * @author DougLei
 */
public class SysUser {
	
	@Column("name")
	private String name;
	
	@Column
	private String liveAddressTest_;
	
	private boolean isNew;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
