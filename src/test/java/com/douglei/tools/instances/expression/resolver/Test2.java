package com.douglei.tools.instances.expression.resolver;

import java.util.Stack;

import org.junit.Test;

public class Test2 {
	
	@Test
	public void test() {
		String e = "1+2*3+8/2-5";
		
		Stack<Integer> integer = new Stack<Integer>();
		
		Stack<Character> s = new Stack<Character>();
		for(int i=0;i<e.length();i++){
			if(isNumber(e.charAt(i))){
				integer.push(Integer.valueOf(e.charAt(i)+""));
			}else{
				char c = e.charAt(i);
				if(!s.isEmpty() && qianMianCanShuDeYouXianJiGao(s.peek(), c)){
					integer.push(oper(integer.pop(), integer.pop(), s.pop()));
				}
				s.push(c);
			}
		}
		
		while(!integer.isEmpty() && integer.size() > 1){
			integer.push(oper(integer.pop(), integer.pop(), s.pop()));
		}
		System.out.println(integer.pop());
	}

	private Integer oper(Integer num1, Integer num2, char c) {
		if(c == '+'){
			return num1+num2;
		}else if(c == '-'){
			return num2-num1;
		}else if(c == '*'){
			return num1*num2;
		}else if(c == '/'){
			return num2/num1;
		}
		return null;
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
