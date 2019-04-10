package com.douglei.instances.expression.resolver;

import java.util.Stack;
import org.junit.Test;


public class Test1 {
	
	@Test
	public void test() {
		String e = "1+2*3+2";
		String se = "";
		
		Stack<Character> s = new Stack<Character>();
		for(int i=0;i<e.length();i++){
			if(isNumber(e.charAt(i))){
				se += e.charAt(i);
			}else{
				char c = e.charAt(i);
				if(!s.isEmpty() && qianMianCanShuDeYouXianJiGao(s.peek(), c)){
					se += s.pop();
				}
				s.push(c);
			}
		}
		
		while(!s.isEmpty()){
			se += s.pop();
		}
		
		System.out.println(se);
	}

	private boolean qianMianCanShuDeYouXianJiGao(char q, char h) {
		return getPriority(q) > getPriority(h);
	}
	
	private int getPriority(char c){
		switch(c){
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			default:
				return 0;
		}
	}
	

	private boolean isNumber(char c) {
		if(c != '+' && c != '-' && c != '*' && c != '/'){
			return true;
		}
		return false;
	}
}
