package com.douglei.tools.instances.parser.expression.operator.arithmetic;

import com.douglei.tools.instances.parser.expression.operator.arithmetic.enums.DataType;

/**
 * +
 * @author DougLei
 */
public class PlusOperatorImpl extends ArithmeticOperator {
	private static final PlusOperatorImpl po = new PlusOperatorImpl(); 
	private PlusOperatorImpl() {
	}
	
	public static final PlusOperatorImpl newInstance(){
		return po;
	}

	public short getPriority() {
		return 11;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		DataType dataType = getMaxDataType(leftParam, rightParam);
		if(dataType == DataType.INTEGER){
			return Integer.valueOf(leftParam) + Integer.valueOf(rightParam);
		}else{
			return Double.valueOf(leftParam) + Double.valueOf(rightParam);
		}
	}
	
	public String getSymbol() {
		return "+";
	}
}
