package com.douglei.instances.expression.resolver;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.douglei.instances.expression.resolver.ExpressionResolverHandler;

public class Test5 {
	
	@Test
	public void test() {
		ExpressionResolverHandler er= ExpressionResolverHandler.newInstance();
		System.out.println(er.resolve("1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8)*  100"));
		System.out.println(er.getPostfixExpression());
		System.out.println(1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8)*100);
		
		System.out.println("-----------------------------------------------");
		
		System.out.println(er.resolve("1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8)*100"));
		System.out.println(1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8)*100);
	
		System.out.println("-----------------------------------------------");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "哈哈");
		System.out.println(er.resolve("name == '哈哈'", map ));
		
		System.out.println("-----------------------------------------------");
		System.out.println(er.resolve("(1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8.0)*100)"));
		System.out.println(er.resolve("(1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8.0)*100) == (0-3216)"));
		System.out.println(er.resolve("(1+2*5-3/8+1-5*8/(5+5)*7-8*5*8/(2+8.0)*100) == (0-3216)").getClass());
//		System.out.println(er.resolve("8/0"));
	}
}
