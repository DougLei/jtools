package com.douglei.instances.expression.resolver.operator.compare;

import com.douglei.instances.expression.resolver.operator.OperatorImpl;
import com.douglei.utils.datatype.ValidationUtil;

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
		String stringValue = leftParam.toString().trim();  
		if(ValidationUtil.isNumber(stringValue)){
			leftParam = Double.valueOf(stringValue);
		}
		stringValue = rightParam.toString().trim();  
		if(ValidationUtil.isNumber(stringValue)){
			rightParam = Double.valueOf(stringValue);
		}
		return !leftParam.equals(rightParam);
	}
	
	public String getSymbol() {
		return "!=";
	}
}
