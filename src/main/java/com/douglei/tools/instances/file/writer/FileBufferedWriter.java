package com.douglei.tools.instances.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.douglei.tools.utils.CloseUtil;

/**
 * 
 * @author DougLei
 */
public class FileBufferedWriter implements AutoCloseable{
	private File targetFile;
	private boolean append;
	private Charset charset = StandardCharsets.UTF_8;
	private BufferedWriter writer;
	
	public FileBufferedWriter() {}
	public FileBufferedWriter(File targetFile) { 
		setTargetFile(targetFile);
	}
	
	/**
	 * 设置要写入的文件
	 * @param targetFile
	 * @return
	 */
	public FileBufferedWriter setTargetFile(File targetFile) {
		this.targetFile = targetFile;
		return this;
	}
	
	/**
	 * 设置是否要追加写入
	 * @param append
	 * @return
	 */
	public FileBufferedWriter setAppend(boolean append) {
		this.append = append;
		return this;
	}
	
	/**
	 * 设置写入的编码
	 * @param charset
	 * @return
	 */
	public FileBufferedWriter setCharset(Charset charset) {
		this.charset = charset;
		return this;
	}
	
	public File getTargetFile() {
		return targetFile;
	}
	
	// 获取写入器
	private BufferedWriter getWriter() throws IOException {
		if(this.writer == null) {
			if(!targetFile.exists()) {
				File folder = targetFile.getParentFile();
				if(!folder.exists()) 
					folder.mkdirs();
				targetFile.createNewFile();
			}
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, append), charset));
		}
		return this.writer;
	}
	
	public void newLine() throws IOException {
		getWriter().newLine();
	}
	public void write(char c) throws IOException {
		getWriter().write(c);
	}
	public void write(String str) throws IOException {
		getWriter().write(str);
	}
	public void writeln(String str) throws IOException {
		getWriter().write(str);
		getWriter().newLine();
	}
	public void writeAndClose(String str) throws IOException {
		getWriter().write(str);
		close();
	}
	
	/**
	 * 关闭
	 */
	public void close() {
		if(writer != null) {
			CloseUtil.closeIO(writer);
			writer = null;
		}
	}
}
