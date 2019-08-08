package com.douglei.tools.utils.naming.column;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.naming.converter.Converter;
import com.douglei.tools.utils.naming.converter.ConverterUtil;
import com.douglei.tools.utils.naming.converter.impl.PropertyName2ColumnNameConverter;

/**
 * 
 * @author DougLei
 */
public class Columns {
	private static final Logger logger = LoggerFactory.getLogger(Columns.class);

	/**
	 * 根据clz的属性, 获取其对应的列名, 多个用,分割
	 * @param clz
	 * @param excludePropertyNames 要排除的属性名
	 * @return
	 */
	public static String getNames(Class<?> clz, String... excludePropertyNames) {
		return getNames(clz, PropertyName2ColumnNameConverter.class, excludePropertyNames);
	}
	
	/**
	 * 根据clz的属性, 获取其对应的列名, 多个用,分割
	 * @param clz
	 * @param converter 指定转换器, 对 {@link @Column} 注解没有指定value的属性名转换
	 * @param excludePropertyNames 要排除的属性名
	 * @return
	 */
	public static String getNames(Class<?> clz, Class<? extends Converter> converter, String... excludePropertyNames) {
		if(logger.isDebugEnabled()) {
			logger.debug("使用转换器[{}], 对[{}]类的属性, 进行列名转换操作", converter.getClass().getName(), clz.getName());
		}
		
		StringBuilder cns = new StringBuilder(400);
		Field[] fields;
		Column column;
		while(clz != Object.class) {
			fields = clz.getDeclaredFields();
			if(fields.length > 0) {
				for (Field field : fields) {
					if((column = field.getAnnotation(Column.class)) != null && unExcludeProperty(field.getName(), excludePropertyNames)) {
						cns.append(", ");
						if(StringUtil.isEmpty(column.value())) {
							cns.append(ConverterUtil.convert(field.getName(), converter));
						}else {
							cns.append(column.value());
						}
					}
				}
			}
			clz = clz.getSuperclass();
		}
		
		if(cns.length() == 0) {
			throw new NullPointerException("在["+clz.getName()+"]类中, 没有匹配到任何可以转换的属性, 请确保在相关属性上使用["+Column.class.getName()+"]注解, 且没有对过多的属性进行排除 (excludePropertyNames)");
		}
		logger.debug("转换得到的列名为 -> {}", cns);
		return cns.substring(2);
	}
	
	/**
	 * 不排除指定的属性
	 * @param propertyName
	 * @param excludePropertyNames
	 * @return
	 */
	private static boolean unExcludeProperty(String propertyName, String[] excludePropertyNames) {
		if(excludePropertyNames.length > 0) {
			for (String excludePropertyName : excludePropertyNames) {
				if(propertyName.equals(excludePropertyName)) {
					return false;
				}
			}
		}
		return true;
	}
}
