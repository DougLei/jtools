package com.douglei.tools.instances.expression.resolver.operator.arithmetic.enums;

import com.douglei.tools.utils.datatype.VerifyTypeMatchUtil;
import com.douglei.tools.utils.datatype.dateformat.DateFormatUtil;

/**
 * 数据类型
 * @author DougLei
 */
public enum DataType {
	INTEGER,
	DOUBLE,
	STRING,
	DATE,
	BOOLEAN;
	
	/**
	 * 获取对应的数据类型
	 * @param value
	 * @return
	 */
	public static DataType getDataType(String value){
		if(VerifyTypeMatchUtil.isInteger(value)){
			return DataType.INTEGER;
		}else if(VerifyTypeMatchUtil.isDouble(value)){
			return  DataType.DOUBLE;
		}else if(VerifyTypeMatchUtil.isBoolean(value)){
			return  DataType.BOOLEAN;
		}else if(DateFormatUtil.verifyIsDate(value)){
			return  DataType.DATE;
		}else{
			return  DataType.STRING;
		}
	}
}
