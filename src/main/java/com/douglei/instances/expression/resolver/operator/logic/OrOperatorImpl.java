package com.douglei.instances.expression.resolver.operator.logic;

/**
 * ||
 * @author DougLei
 */
public class OrOperatorImpl extends LogicOperatorImpl {
	private static final OrOperatorImpl oo = new OrOperatorImpl(); 
	private OrOperatorImpl() {
	}
	
	public static final OrOperatorImpl newInstance(){
		return oo;
	}
	
	public short getPriority() {
		return 3;
	}

	protected boolean logicCalc(boolean leftParam, boolean rightParam) {
		return leftParam || rightParam;
	}

	public String getSymbol() {
		return "||";
	}
}
