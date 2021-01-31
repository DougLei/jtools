package com.douglei.tools;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * 异常工具类
 * @author StoneKing
 */
public class ExceptionUtil {
	
	/**
	 * 获取异常的堆栈跟踪
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		try(PrintWriter pw = new PrintWriter(sw)) {
			t.printStackTrace(pw);
			return sw.toString();
		}
	}
}
