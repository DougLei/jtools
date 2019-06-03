package com.douglei.instances.expression.resolver.operator.compare;

import com.douglei.instances.expression.resolver.operator.OperatorImpl;
import com.douglei.utils.datatype.ValidationUtil;

/**
 * ==
 * @author DougLei
 */
public class EqOperatorImpl extends OperatorImpl {
	private static final EqOperatorImpl eo = new EqOperatorImpl(); 
	private EqOperatorImpl() {
	}
	
	public static final EqOperatorImpl newInstance(){
		return eo;
	}
	
	public short getPriority() {
		return 8;
	}

	protected Object executeCalc(Object leftParam, Object rightParam) {
		if(leftParam == null && rightParam == null){
			return true;
		}
		if((leftParam == null && rightParam != null) || (leftParam != null && rightParam == null)){
			return false;
		}
		String stringValue = leftParam.toString().trim(); 
		if(ValidationUtil.isNumber(stringValue)){
			leftParam = Double.valueOf(stringValue);
		}
		stringValue = rightParam.toString().trim();
		if(ValidationUtil.isNumber(stringValue)){
			rightParam = Double.valueOf(stringValue);
		}
		return leftParam.equals(rightParam);
	}
	
	public String getSymbol() {
		return "==";
	}
}
