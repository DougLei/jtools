package com.douglei.instances.expression.resolver.operator.arithmetic;

import com.douglei.instances.expression.resolver.operator.arithmetic.enums.DataType;

/**
 * %
 * @author DougLei
 */
public class ModOperatorImpl extends ArithmeticOperator {
	private static final ModOperatorImpl mo = new ModOperatorImpl(); 
	private ModOperatorImpl() {
	}
	
	public static final ModOperatorImpl newInstance(){
		return mo;
	}
	
	public short getPriority() {
		return 12;
	}

	protected Object arithmeticCalc(String leftParam, String rightParam) {
		if(rightParam.matches("^0+\\.?0*$")){
			throw new ArithmeticException("除数不能为零");
		}
		DataType dataType = getMaxDataType(leftParam, rightParam);
		if(dataType == DataType.INTEGER){
			return Integer.valueOf(leftParam) % Integer.valueOf(rightParam);
		}else{
			return Double.valueOf(leftParam) % Double.valueOf(rightParam);
		}
	}
	
	public String getSymbol() {
		return "%";
	}
}
