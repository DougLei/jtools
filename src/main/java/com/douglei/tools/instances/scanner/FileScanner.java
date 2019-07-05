package com.douglei.tools.instances.scanner;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

import com.douglei.tools.utils.StringUtil;
import com.douglei.tools.utils.file.FileReaderUtil;

/**
 * 文件扫描器
 * @author StoneKing
 */
public class FileScanner extends Scanner{
	
	public FileScanner(String... targetFileSuffix) {
		super.targetFileSuffix = validateTargetFileSuffix(targetFileSuffix);
	}

	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(boolean searchAllPath, String basePath) {
		if(StringUtil.isEmpty(basePath)){
			throw new NullPointerException("basePath 参数值不能为空");
		}
		
		if(searchAllPath) {
			Enumeration<URL> fileUrls = getResources(basePath);
			while(fileUrls.hasMoreElements()) {
				scan_(fileUrls.nextElement(), basePath);
			}
		}else {
			URL fileUrl = getResource(basePath); // 获取文件在操作系统下的URL路径
			scan_(fileUrl, basePath);
		}
		return list;
	}
	
	private void scan_(URL fileUrl, String basePath) {
		if(fileUrl != null) {
			if(isFile(fileUrl)) {
				scanFromFile(fileUrl.getFile());
			}else if(isJarFile(fileUrl)) {
				scanFromJar(fileUrl, basePath);
			}else {
				// TODO 后续可能需要实现其他protocol
				throw new UnsupportProtocolException(fileUrl);
			}
		}
	}
	
	@Override
	protected Object[] processParamsOnDirectory(File directory, Object... params) {
		return params;
	}
	
	@Override
	protected void addFileToList(File file, Object... params) {
		list.add(file.getAbsolutePath());
	}
	
	@Override
	protected void addJarEntryToList(JarEntry entry) {
		list.add(FileReaderUtil.JAR_FILE + entry.getName());
	}
	
	@Override
	public List<String> rescan(boolean searchAllPath, String basePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(searchAllPath, basePath);
	}
	
	@Override
	public List<String> multiScan(boolean searchAllPath, String... basePaths){
		for (String basePath : basePaths) {
			scan(searchAllPath, basePath);
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(boolean searchAllPath, String... basePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(searchAllPath, basePaths);
	}
	
	@Override
	public List<String> scan(String basePath) {
		return scan(false, basePath);
	}

	@Override
	public List<String> rescan(String basePath) {
		return rescan(false, basePath);
	}

	@Override
	public List<String> multiScan(String... basePaths) {
		return multiScan(false, basePaths);
	}

	@Override
	public List<String> reMultiScan(String... basePaths) {
		return reMultiScan(false, basePaths);
	}
}
