package com.douglei.tools.instances.file.resource.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.CloseUtil;
import com.douglei.tools.utils.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractPropertiesReader extends AbstractResourcesReader{
	private static final Logger logger = LoggerFactory.getLogger(AbstractPropertiesReader.class);
	
	public AbstractPropertiesReader() {
	}
	public AbstractPropertiesReader(String propertiesPath) {
		super(propertiesPath);
	}
	public AbstractPropertiesReader(String propertiesPath, Charset charset) {
		super(propertiesPath, charset);
	}
	public AbstractPropertiesReader(InputStream in) {
		super(in);
	}
	public AbstractPropertiesReader(InputStream in, Charset charset) {
		super(in, charset);
	}
	
	/**
	 * 
	 * @return
	 */
	protected Properties loadProperties() {
		if(in != null) {
			Properties properties = new Properties();
			try {
				if(charset == null) {
					properties.load(in);
				}else {
					java.io.Reader reader = null;
					try {
						reader = new InputStreamReader(in, charset);
						properties.load(reader);
					} finally {
						CloseUtil.closeIO(reader);
					}
				}
			} catch (IOException e) {
				properties = null;
				logger.error("读取配置文件时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			} finally {
				close();
			}
			return properties;
		}
		return null;
	}
}
