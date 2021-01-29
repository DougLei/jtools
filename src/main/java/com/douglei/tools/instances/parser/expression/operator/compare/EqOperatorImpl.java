package com.douglei.tools.instances.parser.expression.operator.compare;

import com.douglei.tools.instances.parser.expression.operator.OperatorImpl;
import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;

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
		if(VerifyTypeMatchUtil.isNumber(stringValue)){
			leftParam = Double.valueOf(stringValue);
		}
		stringValue = rightParam.toString().trim();
		if(VerifyTypeMatchUtil.isNumber(stringValue)){
			rightParam = Double.valueOf(stringValue);
		}
		return leftParam.equals(rightParam);
	}
	
	public String getSymbol() {
		return "==";
	}
}
