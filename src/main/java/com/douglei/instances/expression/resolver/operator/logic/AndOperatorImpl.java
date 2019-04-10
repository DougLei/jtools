package com.douglei.instances.expression.resolver.operator.logic;

/**
 * &&
 * @author DougLei
 */
public class AndOperatorImpl extends LogicOperatorImpl {
	private static final AndOperatorImpl ao = new AndOperatorImpl(); 
	private AndOperatorImpl() {
	}
	
	public static final AndOperatorImpl newInstance(){
		return ao;
	}
	
	public short getPriority() {
		return 4;
	}

	protected boolean logicCalc(boolean leftParam, boolean rightParam) {
		return leftParam && rightParam;
	}
	
	public String getSymbol() {
		return "&&";
	}
}
