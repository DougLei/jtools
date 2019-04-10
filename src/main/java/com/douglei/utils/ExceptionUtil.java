package com.douglei.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 异常工具类
 * @author StoneKing
 */
public class ExceptionUtil {
	
	/**
	 * 获取异常的详细信息
	 * <p>错在哪个类，哪一行</p>
	 * @param t
	 * @return
	 */
	public static String getExceptionDetailMessage(Throwable t){
		String exceptionDetailMessage = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			exceptionDetailMessage = sw.toString();
			pw.close();
		} catch (Exception e) {
			exceptionDetailMessage = "ExceptionUtil.getExceptionDetailMessage(Throwable t)方法时出现异常";
			e.printStackTrace();
		}
		return exceptionDetailMessage;
	}
}
