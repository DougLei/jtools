package com.douglei.tools.instances.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 路径匹配器
 * @author DougLei
 */
public class PathMatcher {
	private static final Logger logger = LoggerFactory.getLogger(PathMatcher.class);
	private List<Pattern> patterns;// 正则数组
	
	public PathMatcher(String... pathPatterns) {
		resetPathPatterns(pathPatterns);
	}
	
	/**
	 * 重置路径pattern
	 * @param pathPatterns
	 */
	public void resetPathPatterns(String... pathPatterns) {
		if(patterns != null && !patterns.isEmpty()) 
			patterns.clear();
		
		if(pathPatterns != null && pathPatterns.length > 0) {
			if(patterns == null) {
				patterns = new ArrayList<Pattern>(pathPatterns.length);
			}
			
			StringBuilder regex = new StringBuilder(50);
			for (String pathPattern : pathPatterns) {
				patterns.add(compile(pathPattern, regex));
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
		pathPattern = processPath(pathPattern);
		String[] pathPatterns = pathPattern.split("/");
		String pp;
		short c_index;
		char c;
		for (byte i=0;i<pathPatterns.length;i++) {
			pp = pathPatterns[i];
			
			if(pp.length() == 0) {
				regex.append("(/){1}"); //开头/会在这个判断中处理, 加一个/匹配
			}else {
				regex.append('(');
				for(c_index = 0; c_index < pp.length(); c_index++) {
					c = pp.charAt(c_index);
					switch(c) {
						// *需要特殊处理
						case '*':
							processRegexLastCharacter(regex, ')');
							if(c_index < pp.length()-1 && pp.charAt(c_index+1) == '*') { // 当前的下标不是pp字符串的最后一位, 判断是否还有双星配置
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
						case ']':
						case '^':
						case '{':
						case '}':
							regex.append('\\');//以上这些都是正则表达式的一些关键字, 追加前要加上转义符
						default:
							regex.append(c);
							break;
					}
				}
				processRegexLastCharacter(regex, ')','{','1','}');
				if(i<pathPatterns.length-1) {//只要不是最后一个, 都要加一个/匹配
					regex.append("(/){1}");
				}
			}
		}
		regex.append("(/)?");
		logger.debug("根据pathPattern={}, 解析出来的正则表达式为={}", pathPattern, regex);
		return Pattern.compile(regex.toString());
	}
	
	/**
	 * 处理正则的最后一个字符, 如果是(, 则移除, 否则使用参数{@code completionChars}补全
	 * @param regex
	 * @param completionChars
	 */
	private void processRegexLastCharacter(StringBuilder regex, char... completionChars) {
		if(regex.charAt(regex.length()-1) == '(') {
			regex.setLength(regex.length()>1?regex.length()-1:0);
		}else {
			for (char completionChar : completionChars) {
				regex.append(completionChar);
			}
		}
	}
	
	/**
	 * 匹配指定路径
	 * @param path 要匹配的path
	 * @return
	 */
	public boolean match(String path) {
		if(patterns!= null) {
			path = processPath(path);
			for (Pattern pattern : patterns) {
				if(pattern.matcher(path).matches()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 处理路径, 如果存在\, 则替换成/
	 * @param path
	 * @return
	 */
	private String processPath(String path) {
		if(path.indexOf("\\") != -1) {
			path = path.replace("\\", "/");
		}
		return path;
	}
}
