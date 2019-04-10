package com.douglei.instances.expression.resolver.operator.compare;

import com.douglei.instances.expression.resolver.operator.OperatorImpl;
import com.douglei.utils.DataTypeValidationUtil;

/**
 * !=
 * @author DougLei
 */
public class NeOperatorImpl extends OperatorImpl {
	private static final NeOperatorImpl no = new NeOperatorImpl(); 
	private NeOperatorImpl() {
	}
	
	public static final NeOperatorImpl newInstance(){
		return no;
	}
	
	public short getPriority() {
		return 8;
	}

	protected Object executeCalc(Object leftParam, Object rightParam) {
		if(leftParam == null && rightParam == null){
			return false;
		}
		if((leftParam == null && rightParam != null) || (leftParam != null && rightParam == null)){
			return true;
		}
		if(DataTypeValidationUtil.isNumber(leftParam)){
			leftParam = Double.valueOf(leftParam.toString());
		}
		if(DataTypeValidationUtil.isNumber(rightParam)){
			rightParam = Double.valueOf(rightParam.toString());
		}
		return !leftParam.equals(rightParam);
	}
	
	public String getSymbol() {
		return "!=";
	}
}
