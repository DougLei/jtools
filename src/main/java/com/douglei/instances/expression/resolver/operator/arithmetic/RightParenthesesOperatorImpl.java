package com.douglei.instances.expression.resolver.operator.arithmetic;

/**
 * )
 * @author DougLei
 */
public class RightParenthesesOperatorImpl extends ArithmeticOperator {
	private static final RightParenthesesOperatorImpl rpo = new RightParenthesesOperatorImpl(); 
	private RightParenthesesOperatorImpl() {
	}
	
	public static final RightParenthesesOperatorImpl newInstance(){
		return rpo;
	}
	
	public short getPriority() {
		return 14;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return null;
	}
	
	public String getSymbol() {
		return ")";
	}
}
