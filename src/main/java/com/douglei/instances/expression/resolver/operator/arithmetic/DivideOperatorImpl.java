package com.douglei.instances.expression.resolver.operator.arithmetic;

import com.douglei.instances.expression.resolver.operator.arithmetic.enums.DataType;



/**
 * 除
 * @author DougLei
 */
public class DivideOperatorImpl extends ArithmeticOperator {
	private static final DivideOperatorImpl dio = new DivideOperatorImpl(); 
	private DivideOperatorImpl() {
	}
	
	public static final DivideOperatorImpl newInstance(){
		return dio;
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
			return Integer.valueOf(leftParam) / Integer.valueOf(rightParam);
		}else{
			return Double.valueOf(leftParam) / Double.valueOf(rightParam);
		}
	}
	
	public String getSymbol() {
		return "/";
	}
}
