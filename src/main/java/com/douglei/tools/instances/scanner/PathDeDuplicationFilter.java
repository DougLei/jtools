package com.douglei.tools.instances.scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 路径去重过滤器
 * 防止重复扫描同一个文件或路径
 * @author DougLei
 */
public class PathDeDuplicationFilter {
	
	private final ScannerType type;
	public PathDeDuplicationFilter(ScannerType type) {
		this.type = type;
	}

	/**
	 * 
	 * @param paths
	 * @return
	 */
	public String[] doFilter(String... paths) {
		if(paths.length < 2) {
			return paths;
		}
		List<String> finalPaths = null;
		int length = paths.length;
		PathWrapper[] wrapper = new PathWrapper[length];
		byte index = 0;
		for (String path : paths) {
			wrapper[index] = new PathWrapper(index, path);
			index++;
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
		return finalPaths.toArray(new String[finalPaths.size()]);
	}
	
	/**
	 * 
	 * @author DougLei
	 */
	private class PathWrapper implements Comparable<PathWrapper>{
		byte index;// 记录路径原来的下标
		byte length;// 是路径的长度, 根据长度进行排序, 短的在前面, 长的在后面
		String originPath;// 记录被操作的路径
		PathWrapper(byte index, String originPath) {
			this.index = index;
			this.originPath = originPath;
			switch(type) {
				case FILE:
					this.length = (byte) originPath.split("/").length;
					break;
				case CLASS:
					this.length = (byte) originPath.split(".").length;
					break;
			}
		}
		
		@Override
		public String toString() {
			return "\nPathWrapper [index=" + index + ", length=" + length + ", originPath=" + originPath + "]";
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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + index;
			result = prime * result + length;
			result = prime * result + originPath.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			PathWrapper other = (PathWrapper) obj;
			return index == other.index && length == other.length && originPath.equals(other.originPath);
		}
	}
}
