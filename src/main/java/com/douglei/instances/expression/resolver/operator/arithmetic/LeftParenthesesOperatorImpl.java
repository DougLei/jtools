package com.douglei.instances.expression.resolver.operator.arithmetic;

/**
 * (
 * @author DougLei
 */
public class LeftParenthesesOperatorImpl extends ArithmeticOperator {
	private static final LeftParenthesesOperatorImpl lpo = new LeftParenthesesOperatorImpl(); 
	private LeftParenthesesOperatorImpl() {
	}
	
	public static final LeftParenthesesOperatorImpl newInstance(){
		return lpo;
	}
	
	public short getPriority() {
		return 14;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return null;
	}
	
	public String getSymbol() {
		return "(";
	}
}
