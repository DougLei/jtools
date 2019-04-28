package com.douglei.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.utils.datatype.ValidationUtil;

/**
 * 日期工具类
 * @author DougLei
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * -格式，简单日期格式化类
	 * <p>日期格式为：yyyy-MM-dd</p>
	 */
	private transient static final SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * -格式，详细日期格式化类
	 * <p>日期格式为：yyyy-MM-dd HH:mm:ss</p>
	 */
	private transient static final SimpleDateFormat sdfDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 带时区的日期格式化类
	 * <p>UTC通用标准时，以Z来标识</p>
	 */
	private transient static final SimpleDateFormat sdfTimeZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
	
	/**
	 * 格式化日期对象为字符串
	 * <p>只包括年月日</p>
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		if(date == null){
			return null;
		}
		return sdfSimple.format(date);
	}
	
	/**
	 * 格式化日期对象为字符串
	 * <p>包括时分秒</p>
	 * @param date
	 * @return
	 */
	public static String formatDatetime(Date date){
		if(date == null){
			return null;
		}
		return sdfDetail.format(date);
	}
	
	/**
	 * 格式化日期字符串为日期对象
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date){
		if(!ValidationUtil.isDate(date)){
			logger.debug("日期字符串 [{}] 的格式错误", date);
			throw new IllegalArgumentException("日期字符串 ["+date+"] 的格式错误");
		}
		try {
			if(date.endsWith("Z")){
				return sdfTimeZone.parse(date.replace("Z", " UTC"));
			}else if(date.indexOf(":") != -1){
				return sdfDetail.parse(date);
			}else{
				return sdfSimple.parse(date);
			}
		} catch (ParseException e) {
			logger.error("格式化日期字符串 [{}] 为日期对象时, 出现异常:{}", date, ExceptionUtil.getExceptionDetailMessage(e));
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 格式化日期字符串为sql类型的日期对象
	 * <p>没有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(String dateStr){
		return new java.sql.Date(parseDate(dateStr).getTime());
	}
	
	/**
	 * 格式化日期字符串为sql类型的日期对象
	 * <p>没有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static java.sql.Date parseSqlDate(Date date){
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * 格式化日期字符串为sql类型的日期对象
	 * <p>有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static Timestamp parseSqlTimestamp(String dateStr){
		return new Timestamp(parseDate(dateStr).getTime());
	}
	
	/**
	 * 格式化日期字符串为sql类型的日期对象
	 * <p>有时分秒</p>
	 * @param dateStr
	 * @return
	 */
	public static Timestamp parseSqlTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	// -----------------------------------------------------------------
	/**
	 * 当前日期是否是当月第一天
	 * @param currentDate
	 * @return
	 */
	public static boolean isFirstDayOfMonth(Date currentDate) {
		if("1".equals(ddSdf.format(currentDate))){
			return true;
		}
		return false;
	}
	/**	dd */
	private static final SimpleDateFormat ddSdf = new SimpleDateFormat("d");
	
	// -----------------------------------------------------------------
	/**
	 * 自定义格式化日期对象为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, SimpleDateFormat sdf){
		if(date == null){
			return null;
		}
		return sdf.format(date);
	}
	
	/**
	 * 自定义格式化日期对象为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	// -----------------------------------------------------------------
	/**
	 * 获取当前日期是今年的第几周
	 * <p>周日历</p>
	 * @param date
	 * @return
	 */
	public static int weekCanlendarOfYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 获取当前日期是第几季度
	 * @param date
	 * @param isNumber 是否是数字，不是数字(1.2.3.4)，就是汉字(一.二.三.四)
	 * @return
	 */
	public static String getSeason(Date date, int isNumber) {
		int month = Integer.valueOf(mmSdf.format(date));
		if(isNumber == 1){
			if(month >0 && month <4){
				return "1";
			}else if(month >3 && month <7){
				return "2";
			}else if(month >6 && month <10){
				return "3";
			}else{
				return "4";
			}
		}else{
			if(month >0 && month <4){
				return "一";
			}else if(month >3 && month <7){
				return "二";
			}else if(month >6 && month <10){
				return "三";
			}else{
				return "四";
			}
		}
	}
	/**	MM */
	private static final SimpleDateFormat mmSdf = new SimpleDateFormat("MM");

	// -----------------------------------------------------------------
	/**
	 * 给指定日期【加/减】天数
	 * @param dateStr 指定日期字符串类型
	 * @param days 正数为加，负数为减
	 * @return
	 */
	public static String addAndSubtractDay(Object date, int days) {
		if(days == 0) {
			logger.debug("加减日期的天数不能为0");
			throw new IllegalArgumentException("加减日期的天数不能为0");
		}
		if(ValidationUtil.isDate(date)){
			long resultDateTime;
			if(date instanceof String){
				resultDateTime = parseDate(date.toString()).getTime() + (days*86400000);
			}else{
				resultDateTime = ((Date)date).getTime() + (days*86400000);
			}
			return formatDate(new Date(resultDateTime));
		}
		logger.debug("参数date的日期值 [{}] 格式错误", date);
		throw new IllegalArgumentException("参数date的日期值 ["+date+"] 格式错误");
	}
	
	// -----------------------------------------------------------------
	/**
	 * 获得当前日期是周几
	 * @return
	 */
	public static String getWeekend() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int w = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(w<0){w=0;}
		return weekDays[w];
	}
	private static final String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
}
