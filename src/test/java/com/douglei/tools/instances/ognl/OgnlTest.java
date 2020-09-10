package com.douglei.tools.instances.ognl;

import java.util.HashMap;
import java.util.Map;

public class OgnlTest {
	
//	@Test
//	public void test1() throws Exception {
//		OgnlContext context = new OgnlContext(null, null, new DefaultMemberAccess(true));
//		System.out.println(Ognl.getValue("address.address != null", context, User.getInstance()));
//	}
	
	public static void main(String[] args) {
		OgnlHandler ognl = OgnlHandler.getSingleton();
		
		
		new Thread(new Runnable() {
			public void run() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", "Douglei");
				
				System.out.println("111-: " + ognl.getBooleanValue("address.address == null", User.getInstance()) + " address.address == null应该为false");
				System.out.println("111-: " + ognl.getBooleanValue("name == null", map) + " name == null应该为false");
				System.out.println("111-: " + ognl.getObjectValue("name", map) + " name应该为Douglei");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("222-: " + ognl.getBooleanValue("address.address == null", User.getOtherInstance()) +" address.address == null应该为true");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("333-: " + ognl.getObjectValue("name", User.getInstance()) + " name应该为金石磊");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("777-: " + ognl.getBooleanValue("address.address == null", User.getInstance()) + " address.address == null应该为false");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("444-: " + ognl.getBooleanValue("address.address != null", User.getOtherInstance()) + " address.address != null应该为false");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("666-: " + ognl.getBooleanValue("address.address == null", User.getOtherInstance()) + " address.address == null应该为true");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("999-: " + ognl.getBooleanValue("address.address == null", User.getInstance()) + " address.address == null应该为false");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", "Douglei2222");
				System.out.println("hhh-: " + ognl.getBooleanValue("name == null", map) + " name == null应该为false");
				System.out.println("hhh-: " + ognl.getObjectValue("name", map) + " name应该为Douglei2222");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("555-: " + ognl.getBooleanValue("address.address != null", User.getOtherInstance()) + " address.address != null应该为false");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("888-: " + ognl.getBooleanValue("address.address == null", User.getInstance()) + " address.address == null应该为false");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				Map<String, Object> map = new HashMap<String, Object>();
				System.out.println("ccc-: " + ognl.getBooleanValue("name == null", map) + " name == null应该为true");
				System.out.println("ccc-: " + ognl.getObjectValue("name", map) + " name应该为null");
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				System.out.println("xxxx-: " + ognl.getBooleanValue("address.address != null", User.getInstance()) + " address.address != null应该为true");
			}
		}).start();
	
		new Thread(new Runnable() {
			public void run() {
				System.out.println("000-: " + ognl.getBooleanValue("address.address == null", User.getOtherInstance()) + " address.address == null应该为true");
			}
		}).start();
	}
	
}
