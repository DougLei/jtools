package com.douglei.tools.i18n;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.douglei.tools.file.reader.PropertiesReader;
import com.douglei.tools.file.scanner.impl.ResourceScanner;

/**
 * 国际化资源
 * @author DougLei
 */
public class I18nResource {
	private static final String suffix = ".i18n"; // 国际化资源文件的后缀
	private String defaultLanguage; // 默认语言
	private MessageContainer container; // 存储国际化信息的容器
	
	public I18nResource() {
		this("zh_CN", new MessageContainerImpl());
	}
	
	/**
	 * 
	 * @param defaultLanguage 默认语言
	 * @param container 存储国际化信息的容器
	 * @param folders 存放国际化资源文件的目录 (基于java resource)
	 */
	public I18nResource(String defaultLanguage, MessageContainer container, String... folders) {
		this.defaultLanguage = defaultLanguage;
		this.container = container;
		
		// 扫描指定目录下的国际化文件, 将国际化信息添加到MessageContainer中
		List<String> files = new ResourceScanner(suffix).multiScan(true, folders.length==0?new String[] {"i18n"}:folders);
		if(files.size() > 0) {
			PropertiesReader reader = null;
			for (String file : files) {
				reader = new PropertiesReader(ResourceScanner.readByScanPath(file), StandardCharsets.UTF_8);
				if(reader.isEmpty())
					continue;
				
				file = file.substring(file.lastIndexOf(File.separatorChar)+1);
				container.putMessage(file.substring(0, file.indexOf(".")), reader.entrySet());
			}
		}
	}
	
	/**
	 * 获取指定语言的消息
	 * @param language
	 * @param code
	 * @param params
	 * @return 
	 */
	public Message getMessage(String language, String code, Object... params) {
		return container.getMessage(language, code, params);
	}
	
	/**
	 * 获取默认语言
	 * @return
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}
}
