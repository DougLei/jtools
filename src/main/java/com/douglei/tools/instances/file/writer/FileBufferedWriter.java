package com.douglei.tools.instances.file.writer;

import java.io.BufferedWriter;
import java.io.File;
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
	private File file;
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
		try {
			setFile_(file);
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setFile(File file, Charset charset) {
		try {
			setFile_(file);
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setFile(File file, boolean append) {
		try {
			setFile_(file);
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setFile(File file, Charset charset, boolean append) {
		try {
			setFile_(file);
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 如果file不存在, 则创建
	private void setFile_(File file) throws IOException {
		if(!file.exists()) {
			File folder = file.getParentFile();
			if(!folder.exists()) {
				folder.mkdirs();
			}
			file.createNewFile();
		}
		this.file = file;
	}
	
	public File getTargetFile() {
		return file;
	}
	public boolean ready() {
		return writer != null;
	}
	public void newLine() throws IOException {
		if(ready()) {
			writer.newLine();
		}
	}
	public void write(char c) throws IOException {
		if(ready()) {
			writer.write(c);
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
