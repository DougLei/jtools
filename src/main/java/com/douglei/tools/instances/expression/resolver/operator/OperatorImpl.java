package com.douglei.tools.instances.expression.resolver.operator;

import java.util.Map;

import com.douglei.tools.utils.StringUtil;

/**
 * 
 * @author DougLei
 */
public abstract class OperatorImpl implements Operator {

	public Object calc(Object leftParam, Object rightParam, Map<String, Object> map) {
		if(StringUtil.isEmpty(leftParam)){
			throw new NullPointerException("计算时，参数leftParam不能为空");
		}
		if(StringUtil.isEmpty(rightParam)){
			throw new NullPointerException("计算时，参数rightParam不能为空");
		}
		return executeCalc(getParamValue(leftParam, map), getParamValue(rightParam, map));
	}
	
	private Object getParamValue(Object param, Map<String, Object> map) {
		if(map != null && map.size() > 0){
			if(map.get(param) != null){
				return map.get(param);
			}
		}
		return param;
	}

	/**
	 * 执行计算
	 * @param leftParam
	 * @param rightParam
	 * @return
	 */
	protected abstract Object executeCalc(Object leftParam, Object rightParam);
}
