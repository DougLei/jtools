package com.douglei.tools.instances.reader;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.douglei.tools.utils.CloseUtil;

/**
 * 
 * @author DougLei
 */
public abstract class Reader {
	public static final String DEFAULT_PATH = "passed in argument type is java.io.InputStream";
	
	protected String path;
	protected InputStream in;
	protected Charset charset;
	
	public Reader() {
	}
	public Reader(String path) {
		setPath(path);
	}
	public Reader(String path, Charset charset) {
		setPath(path, charset);
	}
	public Reader(InputStream in) {
		setInputStream(in);
	}
	public Reader(InputStream in, Charset charset) {
		setInputStream(in, charset);
	}
	
	public void setPath(String path) {
		setPath(path, null);
	}
	public void setPath(String path, Charset charset) {
		this.path = path;
		in = Reader.class.getClassLoader().getResourceAsStream(path);
		this.charset = charset;
		initialSettings();
	}
	public void setInputStream(InputStream in) {
		setInputStream(in, null);
	}
	public void setInputStream(InputStream in, Charset charset) {
		this.path = DEFAULT_PATH;
		this.in = in;
		this.charset = charset;
		initialSettings();
	}
	
	/**
	 * 初始化设置方法
	 * 由子类具体实现
	 */
	protected void initialSettings() {}
	
	/**
	 * 是否可以读取
	 * @return
	 */
	public abstract boolean ready();
	
	protected void close() {
		if(in != null) {
			CloseUtil.closeIO(in);
			in = null;
		}
	}
}
