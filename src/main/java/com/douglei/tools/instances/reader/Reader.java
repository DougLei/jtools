package com.douglei.tools.instances.reader;

import java.io.InputStream;

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
}
