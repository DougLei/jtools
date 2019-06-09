package com.douglei.tools.instances.expression.resolver.operator.compare;

import com.douglei.tools.instances.expression.resolver.operator.arithmetic.ArithmeticOperator;

/**
 * <=
 * @author DougLei
 */
public class LeOperatorImpl extends ArithmeticOperator {
	private static final LeOperatorImpl lo = new LeOperatorImpl(); 
	private LeOperatorImpl() {
	}
	
	public static final LeOperatorImpl newInstance(){
		return lo;
	}
	
	public short getPriority() {
		return 9;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return Double.valueOf(leftParam) <= Double.valueOf(rightParam);
	}
	
	public String getSymbol() {
		return "<=";
	}
}
