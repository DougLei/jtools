package com.douglei.instances.expression.resolver.operator.logic;

/**
 * !
 * @author DougLei
 */
public class NotOperatorImpl extends LogicOperatorImpl {
	private static final NotOperatorImpl no = new NotOperatorImpl(); 
	private NotOperatorImpl() {
	}
	
	public static final NotOperatorImpl newInstance(){
		return no;
	}
	
	public short getPriority() {
		return 13;
	}

	protected boolean logicCalc(boolean leftParam, boolean rightParam) {
		return !leftParam;
	}

	public String getSymbol() {
		return "!";
	}
}
