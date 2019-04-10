package com.douglei.instances.expression.resolver.operator.compare;

import com.douglei.instances.expression.resolver.operator.arithmetic.ArithmeticOperator;

/**
 * >
 * @author DougLei
 */
public class GtOperatorImpl extends ArithmeticOperator {
	private static final GtOperatorImpl go = new GtOperatorImpl(); 
	private GtOperatorImpl() {
	}
	
	public static final GtOperatorImpl newInstance(){
		return go;
	}
	
	public short getPriority() {
		return 9;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		return Double.valueOf(leftParam) > Double.valueOf(rightParam);
	}
	
	public String getSymbol() {
		return ">";
	}
}
