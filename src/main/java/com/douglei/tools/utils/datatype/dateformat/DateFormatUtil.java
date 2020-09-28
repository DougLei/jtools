package com.douglei.tools.utils.datatype.dateformat;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douglei.tools.instances.file.reader.FileBufferedReader;
import com.douglei.tools.instances.scanner.impl.ClassScanner;
import com.douglei.tools.utils.datatype.converter.ConverterUtil;
import com.douglei.tools.utils.reflect.ConstructorUtil;

/**
 * 日期类型工具类
 * @author DougLei
 */
public class DateFormatUtil {
	private static final Map<String, DateFormat> DATE_FORMAT_MAP = new HashMap<String, DateFormat>(8); // 其中的DateFormat实例, 只为了提供{@link DateFormatUtil.format()}存在
	private static final List<DateFormat> DATE_FORMATS = new ArrayList<DateFormat>(4);
	static {
		ClassScanner scanner = new ClassScanner();
		List<String> classes = scanner.scan(DateFormatUtil.class.getPackage().getName() + ".impl");
		for (String clz : classes) {
			register((DateFormat)ConstructorUtil.newInstance(clz));
		}
		loadDateFormatFactories();
	}
	
	/**
	 * 加载date.format.factories配置文件
	 * 用法参考 {@link ConverterUtil}
	 * 自定义的格式化实例需要继承 {@link DateFormat}
	 */
	private static void loadDateFormatFactories() {
		FileBufferedReader reader = new FileBufferedReader("date.format.factories");
		while(reader.ready()) {
			register((DateFormat)ConstructorUtil.newInstance(reader.readLine().trim()));
		}
	}
	
	/**
	 * 注册日期格式化实例
	 * @param dateFormat
	 */
	private static void register(DateFormat dateFormat) {
		DATE_FORMATS.add(dateFormat);
		putDateFormat(dateFormat);
	}
	
	/**
	 * 给DateFormat的Map中加入新的format实例
	 * @param dateFormat
	 */
	private static void putDateFormat(DateFormat dateFormat) {
		DATE_FORMAT_MAP.put(dateFormat.formatPattern(), dateFormat);
	}
	
	/**
	 * 验证是否是日期类型
	 * @param object
	 * @return
	 */
	public static boolean verifyIsDate(Object object) {
		if(object == null) {
			return false;
		}
		if(DateFormat.isDate(object)) {
			return true;
		}
		return verifyIsDate(object.toString());
	}
	
	/**
	 * 验证是否是日期类型
	 * @param dateString
	 * @return
	 */
	public static boolean verifyIsDate(String dateString) {
		for (DateFormat dateFormat : DATE_FORMATS) {
			if(dateFormat.match(dateString)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString){
		for (DateFormat dateFormat : DATE_FORMATS) {
			if(dateFormat.match(dateString)) {
				return dateFormat.parse(dateString);
			}
		}
		throw new UnsupportDateFormatException(dateString);
	}
	
	/**
	 * 格式化为 {@link Date} 类型的日期对象
	 * @param date
	 * @return Date
	 */
	public static Date parseDate(Object date){
		if(date instanceof String) {
			return parseDate(date.toString());
		}else if(date instanceof Date) {
			return (Date)date;
		}else if(date instanceof java.sql.Date || date instanceof java.sql.Timestamp) {
			return new Date(((Date)date).getTime());
		}else if(date instanceof Long) {
			return new Date((long)date);
		}
		throw new IllegalArgumentException("转换为java.util.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	
	/**
	 * 格式化为 {@link java.sql.Date} 类型的日期对象
	 * @param date
	 * @return java.sql.Date
	 */
	public static java.sql.Date parseSqlDate(Object date){
		if(date instanceof String) {
			return new java.sql.Date(parseDate(date.toString()).getTime());
		}else if(date instanceof java.sql.Date) {
			return (java.sql.Date)date;
		}else if(date instanceof Date || date instanceof java.sql.Timestamp) {
			return new java.sql.Date(((Date)date).getTime());
		}else if(date instanceof Long || date.getClass() == long.class) {
			return new java.sql.Date((long)date);
		}
		throw new IllegalArgumentException("要转换为java.sql.Date时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}
	
	/**
	 * 格式化为 {@link Timestamp} 类型的日期对象
	 * @param date
	 * @return Timestamp
	 */
	public static Timestamp parseSqlTimestamp(Object date) {
		if(date instanceof String) {
			return new Timestamp(parseDate(date.toString()).getTime());
		}else if(date instanceof Timestamp) {
			return (Timestamp)date;
		}else if(date instanceof Date || date instanceof java.sql.Date) {
			return new Timestamp(((Date)date).getTime());
		}else if(date instanceof Long || date.getClass() == long.class) {
			return new Timestamp((long)date);
		}
		throw new IllegalArgumentException("要转换为java.sql.Timestamp时，目前不支持传入的date参数类型["+date.getClass().getName()+"]");
	}

	/**
	 * 使用指定的格式去格式化date
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, final String format) {
		DateFormat df = DATE_FORMAT_MAP.get(format);
		if(df == null) {
			df = new DateFormat() {
				private SimpleDateFormat sdf = new SimpleDateFormat(format);
				
				@Override
				protected SimpleDateFormat sdf() {
					return sdf;
				}
				
				@Override
				protected String formatPattern() {
					return format;
				}
				
				@Override
				protected boolean match(String dateString) {
					return false; // 只是为了format Date实例, 所以不实现该方法
				}
			};
			putDateFormat(df);
		}
		return df.format(date);
	}
}
