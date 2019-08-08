package com.douglei.tools.utils.naming.column;

import org.junit.Test;

/**
 * 
 * @author DougLei
 */
public class ColumnsTest {
	
	@Test
	public void test() {
		System.out.println(Columns.getNames(SysUser.class, "name", "createDate"));
	}
}
