package com.douglei.instances.expression.resolver.operator.arithmetic;

import com.douglei.instances.expression.resolver.operator.arithmetic.enums.DataType;

/**
 * -
 * @author DougLei
 */
public class SubtractOperatorImpl extends ArithmeticOperator {
	private static final SubtractOperatorImpl so = new SubtractOperatorImpl(); 
	private SubtractOperatorImpl() {
	}
	
	public static final SubtractOperatorImpl newInstance(){
		return so;
	}
	
	public short getPriority() {
		return 11;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		DataType dataType = getMaxDataType(leftParam, rightParam);
		if(dataType == DataType.INTEGER){
			return Integer.valueOf(leftParam) - Integer.valueOf(rightParam);
		}else{
			return Double.valueOf(leftParam) - Double.valueOf(rightParam);
		}
	}
	
	public String getSymbol() {
		return "-";
	}
}
