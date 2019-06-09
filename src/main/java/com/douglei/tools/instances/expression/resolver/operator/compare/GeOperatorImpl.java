package com.douglei.tools.instances.expression.resolver.operator.compare;

import com.douglei.tools.instances.expression.resolver.operator.arithmetic.ArithmeticOperator;

/**
 * >=
 * @author DougLei
 */
public class GeOperatorImpl extends ArithmeticOperator {
	private static final GeOperatorImpl go = new GeOperatorImpl(); 
	private GeOperatorImpl() {
	}
	
	public static final GeOperatorImpl newInstance(){
		return go;
	}
	
	public short getPriority() {
		return 9;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return Double.valueOf(leftParam) >= Double.valueOf(rightParam);
	}
	
	public String getSymbol() {
		return ">=";
	}
}
