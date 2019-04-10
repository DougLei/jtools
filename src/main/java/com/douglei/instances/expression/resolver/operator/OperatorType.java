package com.douglei.instances.expression.resolver.operator;

import java.util.Map;

import com.douglei.instances.expression.resolver.operator.arithmetic.DivideOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.LeftParenthesesOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.ModOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.MultiplyOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.PlusOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.RightParenthesesOperatorImpl;
import com.douglei.instances.expression.resolver.operator.arithmetic.SubtractOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.EqOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.GeOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.GtOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.LeOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.LtOperatorImpl;
import com.douglei.instances.expression.resolver.operator.compare.NeOperatorImpl;
import com.douglei.instances.expression.resolver.operator.logic.AndOperatorImpl;
import com.douglei.instances.expression.resolver.operator.logic.NotOperatorImpl;
import com.douglei.instances.expression.resolver.operator.logic.OrOperatorImpl;

/**
 * 运算符类型
 * @author DougLei
 */
public enum OperatorType {
	// 算数运算符
	PLUS(PlusOperatorImpl.newInstance()), // +
	SUBTRACT(SubtractOperatorImpl.newInstance()), // -
	MULTIPLY(MultiplyOperatorImpl.newInstance()), // *
	DIVIDE(DivideOperatorImpl.newInstance()), // /
	MOD(ModOperatorImpl.newInstance()), // %
    LEFT_PARENTHESES(LeftParenthesesOperatorImpl.newInstance()), // (
	RIGHT_PARENTHESES(RightParenthesesOperatorImpl.newInstance()), // )
	
	// 条件运算符
	EQ(EqOperatorImpl.newInstance()), // ==
	NE(NeOperatorImpl.newInstance()), // !=
	GT(GtOperatorImpl.newInstance()), // >
	LT(LtOperatorImpl.newInstance()), // <
	GE(GeOperatorImpl.newInstance()), // >=
	LE(LeOperatorImpl.newInstance()), // <=
	
	// 逻辑运算符
	AND(AndOperatorImpl.newInstance()),
	NOT(NotOperatorImpl.newInstance()),
    OR(OrOperatorImpl.newInstance());
    
    private Operator operator;
	private OperatorType(Operator operator) {
		this.operator = operator;
	}
	
	public short getPriority() {
		return operator.getPriority();
	}
	public Operator getOperator() {
		return operator;
	}
	public String getSymbol(){
		return operator.getSymbol();
	}

	/**
	 * 计算
	 * @param stackTop1
	 * @param stackTop2
	 * @param map
	 * @return
	 */
	public Object calc(Object stackTop1, Object stackTop2, Map<String, Object> map){
		// 栈顶的顺序，和实际的顺序正好是相反的
		return getOperator().calc(stackTop2, stackTop1, map);
	}
}
