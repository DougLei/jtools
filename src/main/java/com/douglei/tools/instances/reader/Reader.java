package com.douglei.tools.instances.reader;

import java.io.InputStream;

import com.douglei.tools.utils.CloseUtil;

/**
 * 
 * @author DougLei
 */
public abstract class Reader {
	protected String path;
	protected InputStream in;
	
	public Reader(String path) {
		this.path = path;
		in = Reader.class.getClassLoader().getResourceAsStream(path);
	}
	
	public boolean ready() {
		return in != null;
	}
	
	protected void close() {
		if(in != null) {
			CloseUtil.closeIO(in);
			in = null;
		}
	}
}
