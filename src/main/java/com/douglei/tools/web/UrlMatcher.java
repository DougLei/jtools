package com.douglei.tools.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Url匹配器
 * @author DougLei
 */
public class UrlMatcher {
	private static final Logger logger = LoggerFactory.getLogger(UrlMatcher.class);
	private List<Pattern> patterns;// 正则数组
	
	public UrlMatcher(String... urlPatterns) {
		resetUrlPatterns(urlPatterns);
	}
	
	/**
	 * 重置urlPatterns
	 * @param urlPatterns
	 */
	public void resetUrlPatterns(String... urlPatterns) {
		if(patterns == null) 
			patterns = new ArrayList<Pattern>(urlPatterns.length);
		else if(patterns.size() >0) 
			patterns.clear();
		
		StringBuilder regex = new StringBuilder(50);
		for (String urlPattern : urlPatterns) {
			patterns.add(compile(parseUrlPattern(urlPattern), regex));
			regex.setLength(0);
		}
	}
	
	/**
	 * 编译urlPattern, 获取 {@link Pattern} 实例
	 * @param urlPattern
	 * @param regex
	 * @return 
	 */
	private Pattern compile(String urlPattern, StringBuilder regex) {
		String[] array = urlPattern.split("/");
		String chunk;
		int c_index;
		char c;
		for (int i=0;i<array.length;i++) {
			chunk = array[i];
			
			if(chunk.length() == 0) {
				regex.append("(/){1}");
			}else {
				regex.append('(');
				for(c_index = 0; c_index < chunk.length(); c_index++) {
					c = chunk.charAt(c_index);
					switch(c) {
						case '*': // * 需要特殊处理
							closing(regex);
							if(c_index < chunk.length()-1 && chunk.charAt(c_index+1) == '*') { // 当前的下标不是pp字符串的最后一位, 判断是否还有双星配置
								regex.append(".*");
								c_index++;
							}else {
								regex.append("([^/]*){1}");
							}
							regex.append('(');
							break;
						case '$':
						case '(':
						case ')':
						case '+':
						case '.':
						case '[':
						case '?':
						case '\\':
						case '^':
						case '{':
						case '|':
							regex.append('\\');// 以上这些都是正则表达式的一些关键字, 追加前要加上反斜杠
						default:
							regex.append(c);
							break;
					}
				}
				closing(regex);
				if(i < array.length - 1) //只要不是最后一个, 都要加一个/匹配
					regex.append("(/){1}");
			}
		}
		regex.append("(/)?");
		logger.debug("根据urlPattern={}, 解析出来的正则表达式为={}", array, regex);
		return Pattern.compile(regex.toString());
	}
	
	/**
	 * 匹配指定的url
	 * @param url 要匹配的url
	 * @return
	 */
	public boolean match(String url) {
		if(patterns !=  null && patterns.size() > 0) {
			url = parseUrlPattern(url);
			for (Pattern pattern : patterns) {
				if(pattern.matcher(url).matches()) 
					return true;
			}
		}
		return false;
	}
	
	// 关闭当前的正则表达式
	private void closing(StringBuilder regex) {
		// 移除单独的(, 没必要专门关闭
		if(regex.charAt(regex.length()-1) == '(' && (regex.length()==1 || regex.charAt(regex.length()-2) != '\\')) 
			regex.setLength(regex.length()-1);
		else
			regex.append("){1}");
	}

	// 解析url; 如果存在\, 则替换成/
	private String parseUrlPattern(String urlPattern) {
		if(urlPattern.indexOf("\\") != -1) 
			return urlPattern.replace("\\", "/");
		return urlPattern;
	}
}
