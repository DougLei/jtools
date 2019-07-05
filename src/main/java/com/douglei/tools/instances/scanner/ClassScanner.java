package com.douglei.tools.instances.scanner;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

import com.douglei.tools.utils.StringUtil;

/**
 * 类扫描器
 * @author StoneKing
 */
public class ClassScanner extends Scanner{
	private static final String[] targetClassSuffix_ = {".class"};
	
	public ClassScanner() {
		super.targetFileSuffix = targetClassSuffix_;
	}

	@Override
	public void resetTargetFileSuffix(String... targetFileSuffix) {
	}

	// -----------------------------------------------------------------------------------------------------------
	@Override
	public List<String> scan(boolean searchAllPath, String basePackage) {
		if(StringUtil.isEmpty(basePackage)){
			throw new NullPointerException("basePackagePath 参数值不能为空");
		}
		
		String packagePath = basePackage.replace(".", "/"); // 将包名的小数点，转换成url格式的分隔符，即'/'
		if(searchAllPath) {
			Enumeration<URL> fileUrls = getResources(packagePath);
			while(fileUrls.hasMoreElements()) {
				scan_(fileUrls.nextElement(), basePackage, packagePath);
			}
		}else {
			URL fileUrl = getResource(packagePath); // 获取包在操作系统下的URL路径
			scan_(fileUrl, basePackage, packagePath);
		}
		return list;
	}
	
	private void scan_(URL fileUrl, String pagekage_, String packagePath) {
		if(fileUrl != null) {
			if(isFile(fileUrl)) {
				scanFromFile(fileUrl.getFile(), pagekage_);
			}else if(isJarFile(fileUrl)) {
				scanFromJar(fileUrl, packagePath);
			}else {
				// TODO 后续可能需要实现其他protocol
				throw new UnsupportProtocolException(fileUrl);
			}
		}
	}
	
	/**
	 * 扫描文件，并加入到list集合中
	 * @param filePath
	 * @param pagekage_
	 */
	private void scanFromFile(String filePath, String pagekage_) {
		File[] files = listFiles(new File(filePath));
		if(files != null && files.length > 0){
			String fileName = null;
			for (File file : files) {
				fileName = file.getName();
				if(file.isDirectory()) {
					scanFromFile(file.getAbsolutePath(), pagekage_+"."+fileName);
				}else {
					list.add(pagekage_ + "." + fileName.substring(0, fileName.length()-6));
				}
			}
		}
	}
	
	@Override
	protected void addJarEntryToList(JarEntry entry) {
		list.add(entry.getName().replace("/", ".").substring(0, entry.getName().length()-6));
	}
	
	@Override
	public List<String> rescan(boolean searchAllPath, String basePackagePath) {
		if(list.size() > 0) {
			list.clear();
		}
		return scan(searchAllPath, basePackagePath);
	}
	
	@Override
	public List<String> multiScan(boolean searchAllPath, String... basePackagePaths){
		for (String basePackagePath : basePackagePaths) {
			scan(searchAllPath, basePackagePath);
		}
		return list;
	}
	
	@Override
	public List<String> reMultiScan(boolean searchAllPath, String... basePackagePaths) {
		if(list.size() > 0) {
			list.clear();
		}
		return multiScan(searchAllPath, basePackagePaths);
	}
	
	@Override
	public List<String> scan(String basePackagePath) {
		return scan(false, basePackagePath);
	}

	@Override
	public List<String> rescan(String basePackagePath) {
		return rescan(false, basePackagePath);
	}

	@Override
	public List<String> multiScan(String... basePackagePaths) {
		return multiScan(false, basePackagePaths);
	}

	@Override
	public List<String> reMultiScan(String... basePackagePaths) {
		return reMultiScan(false, basePackagePaths);
	}
}
