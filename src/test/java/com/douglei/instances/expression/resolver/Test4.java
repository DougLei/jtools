package com.douglei.instances.expression.resolver;

import java.util.Stack;
import org.junit.Test;

public class Test4 {
	
	@Test
	public void test() {
		String e = "(1+2)*3-(5*8)";
		
		char opThis;
		
		Stack<Integer> integer = new Stack<Integer>();
		
		Stack<Character> s = new Stack<Character>();
		for(int i=0;i<e.length();i++){
			opThis = e.charAt(i);
			if(isNumber(opThis)){
				integer.push(Integer.valueOf(opThis+""));
			}else{
				if(s.isEmpty()){
					s.push(opThis);
				}else{
					if(opThis == ')'){
						while(true){
							opThis = s.pop();
							if(opThis != '('){
								integer.push(oper(integer.pop(), integer.pop(), opThis));
							}else{
								break;
							}
						}
					}else{
						if(qianMianCanShuDeYouXianJiGaoHuoPingJi(s.peek(), opThis) && s.peek() != '('){
							integer.push(oper(integer.pop(), integer.pop(), s.pop()));
						}
						s.push(opThis);
					}
				}
			}
		}
		
		while(!s.isEmpty()){
			integer.push(oper(integer.pop(), integer.pop(), s.pop()));
		}
		System.out.println(integer.pop());
	}

	private boolean qianMianCanShuDeYouXianJiGaoHuoPingJi(char q, char h) {
		return (getPriority(q) > getPriority(h)) || (getPriority(q) == getPriority(h));
	}
	
	private int getPriority(char c){
		switch(c){
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '(':
			case ')':
				return 3;
			default:
				return 0;
		}
	}
	

	private boolean isNumber(char c) {
		if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')'){
			return true;
		}
		return false;
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
}
