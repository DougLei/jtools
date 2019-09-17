package com.douglei.tools.instances.path.match;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 路径匹配器
 * @author DougLei
 */
public class PathMatcher {
	private boolean matchAny; // 标识当前的匹配器, 是否匹配所有路径
	private List<Pattern> patterns;// 正则数组
	
	public PathMatcher() {
	}
	public PathMatcher(String... pathPatterns) {
		setPathPatterns(pathPatterns);
	}
	
	/**
	 * set路径pattern, 即重置
	 * @param pathPatterns
	 */
	public void setPathPatterns(String... pathPatterns) {
		matchAny = false;
		if(patterns != null && patterns.size() > 0) {
			patterns.clear();
		}
		if(pathPatterns != null && pathPatterns.length > 0) {
			if(patterns == null) {
				patterns = new ArrayList<Pattern>(pathPatterns.length);
			}
			
			StringBuilder regex = new StringBuilder(50);
			Pattern pattern;
			for (String pathPattern : pathPatterns) {
				if((pattern = compile(pathPattern, regex)) == null) {
					break;
				}
				patterns.add(pattern);
				regex.setLength(0);
			}
		}
	}
	
	/**
	 * 编译pathPattern, 获取 {@link Pattern} 实例
	 * @param pathPattern
	 * @param regex
	 * @return 如果返回null, 则表示可以匹配任意path
	 */
	private Pattern compile(String pathPattern, StringBuilder regex) {
		short index = (short) pathPattern.indexOf("**");
		if(index != -1) {
			if(index == 0) { // 如果双星在开头, 证明要全部匹配了, 则直接结束
				if(patterns.size() > 0) patterns.clear();
				matchAny = true;
				return null;
			}
			pathPattern = pathPattern.substring(0, index+1);// 否则双星前的还要有相应的匹配格式, 所以提取出来, 进行处理
		}
		
		char c;
		for(index = 0; index < pathPattern.length(); index++) {
			c = pathPattern.charAt(index);
			switch(c) {
				// 以下这些都是正则表达式的一些关键字
				case '$':
					regex.append('\\').append('$');
					break;
				case '(':
					regex.append('\\').append('(');
					break;
				case ')':
					regex.append('\\').append(')');
					break;
				case '\\':
					regex.append('\\').append('\\');
					break;
				case '+':
					regex.append('\\').append('+');
					break;
				case '.':
					regex.append('\\').append('.');
					break;
				case '[':
					regex.append('\\').append('[');
					break;
				case ']':
					regex.append('\\').append(']');
					break;
				case '^':
					regex.append('\\').append('^');
					break;
				case '{':
					regex.append('\\').append('{');
					break;
				case '}':
					regex.append('\\').append('}');
					break;
				case '*':
					regex.append('.').append('*');
					break;
				default:
					regex.append(c);
					break;
			}
		}
		return Pattern.compile(regex.toString());
	}
	
	/**
	 * 匹配指定路径
	 * @param path 要匹配的path
	 * @return
	 */
	public boolean match(String path) {
		if(matchAny) {
			return true;
		}
		if(patterns!= null) {
			for (Pattern pattern : patterns) {
				if(pattern.matcher(path).matches()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 销毁对象
	 */
	public void destroy() {
		if(patterns != null) {
			patterns.clear();
			patterns = null;
		}
	}
}
