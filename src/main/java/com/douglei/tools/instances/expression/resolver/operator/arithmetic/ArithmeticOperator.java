package com.douglei.tools.instances.expression.resolver.operator.arithmetic;

import com.douglei.tools.instances.expression.resolver.operator.OperatorImpl;
import com.douglei.tools.instances.expression.resolver.operator.arithmetic.enums.DataType;
import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;

/**
 * 算数运算
 * @author DougLei
 */
public abstract class ArithmeticOperator extends OperatorImpl {

	protected Object executeCalc(Object leftParam, Object rightParam) {
		if(StringUtil.isEmpty(leftParam)){
			throw new NullPointerException("进行算数运算时，参数leftParam不能为空");
		}
		if(StringUtil.isEmpty(rightParam)){
			throw new NullPointerException("进行算数运算时，参数rightParam不能为空");
		}
		String leftParamString = leftParam.toString().trim();
		if(!VerifyTypeMatchUtil.isNumber(leftParamString)){
			throw new NullPointerException("进行算数运算时，参数leftParam的值["+leftParam+"]必须为数字类型");
		}
		String rightParamString = rightParam.toString().trim();
		if(!VerifyTypeMatchUtil.isNumber(rightParamString)){
			throw new NullPointerException("进行算数运算时，参数rightParam的值["+rightParam+"]必须为数字类型");
		}
		return arithmeticCalc(leftParamString, rightParamString);
	}
	
	/**
	 * 算数运算
	 * @param leftParam
	 * @param rightParam
	 * @return
	 */
	protected abstract Object arithmeticCalc(String leftParam, String rightParam);
	
	/**
	 * 取范围最大的数据类型
	 * <p>例如，一个参数是Integer类型，一个参数是Double类型，则返回Double类型</p>
	 * @param leftParam
	 * @param rightParam
	 * @return
	 */
	protected DataType getMaxDataType(String leftParam, String rightParam){
		DataType dt = DataType.getDataType(leftParam);
		if(dt == DataType.DOUBLE){
			return dt;
		}
		return DataType.getDataType(rightParam);
	}
}
