package com.douglei.instances.expression.resolver.operator.logic;

import com.douglei.instances.expression.resolver.operator.OperatorImpl;
import com.douglei.utils.StringUtil;
import com.douglei.utils.datatype.ValidationUtil;

/**
 * 
 * @author DougLei
 */
public abstract class LogicOperatorImpl extends OperatorImpl {

	protected Object executeCalc(Object leftParam, Object rightParam) {
		if(StringUtil.isEmpty(leftParam)){
			throw new NullPointerException("进行逻辑运算时，参数leftParam不能为空");
		}
		if(StringUtil.isEmpty(rightParam)){
			throw new NullPointerException("进行逻辑运算时，参数rightParam不能为空");
		}
		String leftParamString = leftParam.toString().trim();
		if(!ValidationUtil.isBoolean(leftParamString)){
			throw new NullPointerException("进行逻辑运算时，参数leftParam的值["+leftParam+"]必须为boolean类型");
		}
		String rightParamString = rightParam.toString().trim();
		if(!ValidationUtil.isBoolean(rightParamString)){
			throw new NullPointerException("进行逻辑运算时，参数rightParam的值["+rightParam+"]必须为boolean类型");
		}
		return logicCalc(Boolean.valueOf(leftParamString), Boolean.valueOf(rightParamString));
	}

	/**
	 * 逻辑计算
	 * @param leftParam
	 * @param rightParam
	 * @return
	 */
	protected abstract boolean logicCalc(boolean leftParam, boolean rightParam);
}
