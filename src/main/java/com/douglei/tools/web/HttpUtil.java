package com.douglei.tools.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.douglei.tools.UtilRuntimeException;

/**
 * http工具类
 * @author DougLei
 */
public class HttpUtil {

	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String xff = request.getHeader("x-forwarded-for");
		if(xff == null) 
			// 是直接请求, 中间没有经过代理服务器, 所以直接获取客户端ip
			// 这个ip是浏览器自提供的, 无法伪造, 是最安全的ip
			return request.getRemoteAddr();
		
		// 如果是多级代理, ip会通过,分割, 第一个ip就是原始客户端的ip, 如果只有一个ip, 那就是客户端ip
		// 这样需要代理服务器开启类似 x-forwarded-for 功能
		int clientIpIndex = xff.indexOf(","); 
		if(clientIpIndex == -1) 
			return xff;
		return xff.substring(0, clientIpIndex);
	}
	
	/**
	 * 获取Http请求体转换的字符串
	 * @param request
	 * @return
	 */
	public static String getRequestBody2String(HttpServletRequest request) {
		if(request.getContentLength() > 0) {
			try(BufferedReader br = new BufferedReader(request.getReader())){
				StringBuilder requestBody = new StringBuilder(request.getContentLength());
				while(br.ready()) 
					requestBody.append(br.readLine().trim());
				return br.toString();
			} catch (IOException e) {
				throw new UtilRuntimeException("获取Http请求体转换的字符串时出现异常", e);
			}
		}
		return null;
	}
}
