package com.douglei.instances.expression.resolver.operator.arithmetic.enums;

import com.douglei.utils.DataTypeValidationUtil;

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
		if(DataTypeValidationUtil.isInteger(value)){
			return DataType.INTEGER;
		}else if(DataTypeValidationUtil.isFloat(value)){
			return  DataType.DOUBLE;
		}else if(DataTypeValidationUtil.isBoolean(value)){
			return  DataType.BOOLEAN;
		}else if(DataTypeValidationUtil.isDate(value)){
			return  DataType.DATE;
		}else{
			return  DataType.STRING;
		}
	}
}
