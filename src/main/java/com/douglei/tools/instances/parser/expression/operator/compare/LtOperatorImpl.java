package com.douglei.tools.instances.parser.expression.operator.compare;

import com.douglei.tools.instances.parser.expression.operator.arithmetic.ArithmeticOperator;

/**
 * <
 * @author DougLei
 */
public class LtOperatorImpl extends ArithmeticOperator {
	private static final LtOperatorImpl lo = new LtOperatorImpl(); 
	private LtOperatorImpl() {
	}
	
	public static final LtOperatorImpl newInstance(){
		return lo;
	}
	
	public short getPriority() {
		return 9;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return Double.valueOf(leftParam) < Double.valueOf(rightParam);
	}
	
	public String getSymbol() {
		return "<";
	}
}
