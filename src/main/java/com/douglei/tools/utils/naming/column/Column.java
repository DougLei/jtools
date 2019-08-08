package com.douglei.tools.utils.naming.column;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author DougLei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	
	/**
	 * 如果配置了列名, 则使用配置的列名, 否则根据属性名反向转换成列名
	 * @return
	 */
	String value() default "";
}
