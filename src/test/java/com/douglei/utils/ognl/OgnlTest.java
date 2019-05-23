package com.douglei.utils.ognl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlTest {
	
//	@Test
//	public void test1() throws Exception {
//		OgnlContext context = new OgnlContext(null, null, new DefaultMemberAccess(true));
//		System.out.println(Ognl.getValue("address.address != null", context, User.getInstance()));
//	}
	
	public static void main(String[] args) {
		final DefaultMemberAccess dma = new DefaultMemberAccess(true);
		final OgnlContext context = new OgnlContext(null, null, dma);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", "Douglei");
					
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals(false, Ognl.getValue("address.address == null", context, User.getInstance()));
					Assert.assertEquals(false, Ognl.getValue("name == null", context, map));
					System.out.println(Ognl.getValue("name == null", context, map));
					System.out.println(Ognl.getValue("name", context, map));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals(true, Ognl.getValue("address.address == null", context, User.getOtherInstance()));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals("金石磊", Ognl.getValue("name", context, User.getInstance()));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals(false, Ognl.getValue("address.address != null", context, User.getOtherInstance()));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals(false, Ognl.getValue("address.address == null", context, User.getInstance()));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ":\t");
					Assert.assertEquals(true, Ognl.getValue("address.address == null", context, User.getOtherInstance()));
				} catch (OgnlException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
}
