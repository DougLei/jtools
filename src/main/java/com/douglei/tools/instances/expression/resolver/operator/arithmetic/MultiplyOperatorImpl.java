package com.douglei.tools.instances.expression.resolver.operator.arithmetic;

import com.douglei.tools.instances.expression.resolver.operator.arithmetic.enums.DataType;

/**
 * *
 * @author DougLei
 */
public class MultiplyOperatorImpl extends ArithmeticOperator {
	private static final MultiplyOperatorImpl mo = new MultiplyOperatorImpl(); 
	private MultiplyOperatorImpl() {
	}
	
	public static final MultiplyOperatorImpl newInstance(){
		return mo;
	}
	
	public short getPriority() {
		return 12;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		DataType dataType = getMaxDataType(leftParam, rightParam);
		if(dataType == DataType.INTEGER){
			return Integer.valueOf(leftParam) * Integer.valueOf(rightParam);
		}else{
			return Double.valueOf(leftParam) * Double.valueOf(rightParam);
		}
	}
	
	public String getSymbol() {
		return "*";
	}
}
