package com.douglei.instances.expression.resolver.operator.arithmetic.enums;

import com.douglei.utils.datatype.ValidationUtil;

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
		if(ValidationUtil.isInteger(value)){
			return DataType.INTEGER;
		}else if(ValidationUtil.isDouble(value)){
			return  DataType.DOUBLE;
		}else if(ValidationUtil.isBoolean(value)){
			return  DataType.BOOLEAN;
		}else if(ValidationUtil.isDate(value)){
			return  DataType.DATE;
		}else{
			return  DataType.STRING;
		}
	}
}
