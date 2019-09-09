package com.douglei.tools.instances.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.douglei.tools.utils.CloseUtil;

/**
 * 
 * @author DougLei
 */
public class FileBufferedWriter {
	private BufferedWriter writer;
	
	public FileBufferedWriter() {
	}
	public FileBufferedWriter(File file) {
		setFile(file);
	}
	public FileBufferedWriter(File file, Charset charset) {
		setFile(file, charset);
	}
	public FileBufferedWriter(File file, boolean append) {
		setFile(file, append);
	}
	public FileBufferedWriter(File file, Charset charset, boolean append) {
		setFile(file, charset, append);
	}
	
	public void setFile(File file) {
		if(file.exists()) {
			try {
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void setFile(File file, Charset charset) {
		if(file.exists()) {
			try {
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void setFile(File file, boolean append) {
		if(file.exists()) {
			try {
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void setFile(File file, Charset charset, boolean append) {
		if(file.exists()) {
			try {
				this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), charset));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean ready() {
		return writer != null;
	}
	public void newLine() throws IOException {
		if(ready()) {
			writer.newLine();
		}
	}
	public void write(String str) throws IOException {
		if(ready()) {
			writer.write(str);
		}
	}
	public void close() {
		if(ready()) {
			CloseUtil.closeIO(writer);
		}
	}
}
