package com.douglei.tools.datatype;

import java.util.Date;
import java.util.regex.Pattern;

import com.douglei.tools.UtilRuntimeException;
import com.douglei.tools.datatype.dateformat.AbstractDateFormat;

/**
 * 数据类型验证工具类
 * @author StoneKing
 */
public class DataTypeValidateUtil {
	/** 判断整型格式的正则表达式 */
	private static final Pattern integerTypePattern = Pattern.compile("(\\+|-)?[0-9]+");
	/** 判断浮点型格式的正则表达式 */
	private static final Pattern doubleTypePattern = Pattern.compile("(\\+|-)?[0-9]+.[0-9]+");
	
	/**
	 * 是否是字节; 限定在byte的长度范围
	 * @param str
	 * @return
	 */
	public static boolean isLimitByte(String str){
		if(str != null){
			long l;
			if((l =isInteger_(str)) > -1) 
				return l>= Byte.MIN_VALUE && l<= Byte.MAX_VALUE;
		}
		return false;
	}
	
	/**
	 * 是否是短整型; 限定在short的长度范围
	 * @param str
	 * @return
	 */
	public static boolean isLimitShort(String str){
		if(str != null){
			long l;
			if((l =isInteger_(str)) > -1) 
				return l>= Short.MIN_VALUE && l<= Short.MAX_VALUE;
		}
		return false;
	}
	
	/**
	 * 是否是整型; 限定在int的长度范围
	 * @param str
	 * @return
	 */
	public static boolean isLimitInteger(String str){
		if(str != null){
			long l;
			if((l =isInteger_(str)) > -1) 
				return l>= Integer.MIN_VALUE && l<= Integer.MAX_VALUE;
		}
		return false;
	}
	
	/**
	 * 是否是长整型; 限定在long的长度范围
	 * @param str
	 * @return
	 */
	public static boolean isLimitLong(String str){
		if(str != null){
			long l;
			if((l =isInteger_(str)) > -1) 
				return l>= Long.MIN_VALUE && l<= Long.MAX_VALUE;
		}
		return false;
	}
	
	// 是否是整数, 返回-1表示不是整数
	private static long isInteger_(String str) {
		if(integerTypePattern.matcher(str).matches())
			return Long.parseLong(str);
		return -1;
	}
	
	/**
	 * 判断是否是整型
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if(str != null)
			return integerTypePattern.matcher(str).matches();
		return false;
	}
	
	/**
	 * 是否是浮点型; 限定在必须有小数
	 * @param str
	 * @return
	 */
	public static boolean isLimitDouble(String str){
		if(str != null)
			return doubleTypePattern.matcher(str).matches();
		return false;
	}
	
	/**
	 * 是否是数字类型
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value){
		return isLimitDouble(value) || isInteger(value);
	}
	
	/**
	 * 是否是boolean
	 * @param str
	 * @return
	 */
	public static boolean isBoolean(String str){
		return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
	}
	
	/**
	 * 验证是否是日期类型
	 * @param object
	 * @return
	 */
	public static boolean isDate(Object obj) {
		if(obj == null) 
			return false;
		
		if(obj instanceof String) {
			for(AbstractDateFormat df : DateFormatUtil.DATE_FORMATS) {
				if(df.isDate(obj.toString()))
					return true;
			}
			throw new UtilRuntimeException("目前不支持判断["+obj.toString()+"]是否是日期类型");
		}
		return obj instanceof Date || obj instanceof Long || obj.getClass() == long.class;
	}
	
	/**
	 * 判断value是否是简单的数据类型; 例如: String, Date, Integer...
	 * @param value
	 * @return
	 */
	public static boolean isSimpleDataType(Object value) {
		if(value == null)
			return false;
		return DataTypeConvertUtil.DATA_TYPE_CONVERTER_MAP.get(value.getClass()).isSimple();
	}
}
