package com.douglei.tools.instances.reader;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.douglei.tools.utils.CloseUtil;

/**
 * 
 * @author DougLei
 */
public abstract class Reader {
	private static final String DEFAULT_PATH="passed in argument type is java.io.InputStream";
	
	protected String path;
	protected InputStream in;
	protected Charset charset;
	
	public Reader(String path) {
		this(path, null);
	}
	public Reader(String path, Charset charset) {
		this.path = path;
		in = Reader.class.getClassLoader().getResourceAsStream(path);
		this.charset = charset;
	}
	
	public Reader(InputStream in) {
		this(in, null);
	}
	public Reader(InputStream in, Charset charset) {
		this.path = DEFAULT_PATH;
		this.in = in;
		this.charset = charset;
	}
	
	public abstract boolean ready();
	
	protected void close() {
		if(in != null) {
			CloseUtil.closeIO(in);
			in = null;
		}
	}
}
