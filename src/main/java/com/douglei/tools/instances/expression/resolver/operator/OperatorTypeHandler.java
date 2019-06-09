package com.douglei.tools.instances.expression.resolver.operator;

import com.douglei.tools.utils.StringUtil;

/**
 * 操作符类型
 * @author DougLei
 */
public class OperatorTypeHandler {
	
	/**
	 * 当前操作符最大的长度值
	 * <p>and</p>
	 */
	public static final int maxOperatorStrLength = 3;
	
	/**
	 * 获取对应的操作符类型
	 * @param operatorStr
	 * @return
	 */
	public static OperatorType getOperatorType(String operatorStr){
		if(StringUtil.isEmpty(operatorStr)){
			return null;
		}
		if("+".equals(operatorStr)){
			return OperatorType.PLUS;
		}else if("-".equals(operatorStr)){
			return OperatorType.SUBTRACT;
		}else if("*".equals(operatorStr)){
			return OperatorType.MULTIPLY;
		}else if("/".equals(operatorStr)){
			return OperatorType.DIVIDE;
		}else if("%".equals(operatorStr)){
			return OperatorType.MOD;
		}else if("(".equals(operatorStr)){
			return OperatorType.LEFT_PARENTHESES;
		}else if(")".equals(operatorStr)){
			return OperatorType.RIGHT_PARENTHESES;
		}else if("==".equals(operatorStr)){
			return OperatorType.EQ;
		}else if("!=".equals(operatorStr) || "<>".equals(operatorStr)){
			return OperatorType.NE;
		}else if(">".equals(operatorStr)){
			return OperatorType.GT;
		}else if("<".equals(operatorStr)){
			return OperatorType.LT;
		}else if(">=".equals(operatorStr)){
			return OperatorType.GE;
		}else if("<=".equals(operatorStr)){
			return OperatorType.LE;
		}else if("&&".equals(operatorStr) || "and".equalsIgnoreCase(operatorStr)){
			return OperatorType.AND;
		}else if("||".equals(operatorStr) || "or".equalsIgnoreCase(operatorStr)){
			return OperatorType.OR;
		}else if("!".equals(operatorStr)){
			return OperatorType.NOT;
		}
		return null;
	}
}
