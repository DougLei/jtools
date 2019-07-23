package com.douglei.tools.utils.http;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author DougLei
 */
public class HttpRequestUtil {

	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String xff = request.getHeader("x-forwarded-for");
		if(xff == null) {
			// 是直接请求, 中间没有经过代理服务器, 所以直接获取客户端ip
			// 这个ip是浏览器自提供的, 无法伪造, 是最安全的ip
			return request.getRemoteAddr();
		}
		
		// 如果是多级代理, ip会通过,分割, 第一个ip就是原始客户端的ip, 如果只有一个ip, 那就是客户端ip
		// 这样需要代理服务器开启类似 x-forwarded-for 功能
		int clientIpIndex = xff.indexOf(","); 
		if(clientIpIndex == -1) {
			return xff;
		}
		return xff.substring(0, clientIpIndex);
	}
}
