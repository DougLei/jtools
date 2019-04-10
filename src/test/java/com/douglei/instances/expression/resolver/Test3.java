package com.douglei.instances.expression.resolver;

import java.util.Stack;
import org.junit.Test;

public class Test3 {
	
	@Test
	public void test() {
		String e = "A*((B+C))";
		String se = "";
		
		char opThis;
		
		Stack<Character> s = new Stack<Character>();
		for(int i=0;i<e.length();i++){
			opThis = e.charAt(i);
			if(isNumber(opThis)){
				se += opThis;
			}else{
				if(s.isEmpty()){
					s.push(opThis);
				}else{
					if(opThis == ')'){
						while(true){
							opThis = s.pop();
							if(opThis != '('){
								se += opThis;
							}else{
								break;
							}
						}
					}else{
						if(qianMianCanShuDeYouXianJiGaoHuoPingJi(s.peek(), opThis) && s.peek() != '('){
							se += s.pop();
						}
						s.push(opThis);
					}
				}
			}
		}
		
		while(!s.isEmpty()){
			se += s.pop();
		}
		System.out.println(se);
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
}
