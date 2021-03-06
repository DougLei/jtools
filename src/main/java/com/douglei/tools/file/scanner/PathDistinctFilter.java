package com.douglei.tools.file.scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 路径去重过滤器, 防止重复扫描同一个文件或路径
 * @author DougLei
 */
public class PathDistinctFilter {
	private final String separator;
	
	/**
	 * 路径分隔符
	 * @param splitChar
	 */
	PathDistinctFilter(String separator) {
		this.separator = separator;
	}

	/**
	 * 去重
	 * @param paths
	 * @return
	 */
	public String[] distinct(String... paths) {
		if(paths.length < 2) {
			return paths;
		}
		List<String> finalPaths = null;
		int length = paths.length;
		PathWrapper[] wrapper = new PathWrapper[length];
		byte index = 0;
		for (String path : paths) {
			wrapper[index++] = new PathWrapper(path);
		}
		Arrays.sort(wrapper);
		
		finalPaths = new ArrayList<String>(length);
		String originPath = null;
		for (int i = 0; i < length; i++) {
			if(wrapper[i] == null) {
				continue;
			}
			
			originPath = wrapper[i].originPath;
			finalPaths.add(originPath);
			if(i == length-1) {
				break;
			}
			
			for (int j = i+1; j < length; j++) {
				if(wrapper[j] != null && wrapper[j].originPath.startsWith(originPath)) {
					wrapper[j] = null;
				}
			}
		}
		
		if(finalPaths.size() == length) {
			return paths;
		}
		return finalPaths.toArray(new String[finalPaths.size()]);
	}
	
	/**
	 * 
	 * @author DougLei
	 */
	private class PathWrapper implements Comparable<PathWrapper>{
		int length;// 是路径的长度, 根据长度进行排序, 短的在前面, 长的在后面
		String originPath;// 记录被操作的路径
		PathWrapper(String originPath) {
			this.originPath = (originPath.indexOf("\\")!=-1)?originPath.replace("\\", "/"):originPath;
			this.length = this.originPath.split(separator).length;
		}
		
		@Override
		public int compareTo(PathWrapper o) {
			if(this.length < o.length) {
				return -1;
			}else if(this.length == o.length) {
				return 0;
			}else {
				return 1;
			}
		}
		
		@Override
		public String toString() {
			return "\nPathWrapper [length=" + length + ", originPath=" + originPath + "]";
		}
	}
}
