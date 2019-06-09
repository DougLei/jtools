package com.douglei.tools.instances.expression.resolver.operator;

import java.util.Map;


/**
 * 运算符
 * @author DougLei
 */
public interface Operator {

	/**
	 * 获取优先级
	 * <p>越大的越优先</p>
	 * @return
	 */
	short getPriority();
	
	/**
	 * 获取符号
	 * @return
	 */
	String getSymbol();
	
	/**
	 * 计算
	 * @param leftParam
	 * @param rightParam
	 * @param map
	 * @return
	 */
	Object calc(Object leftParam, Object rightParam, Map<String, Object> map);
}
