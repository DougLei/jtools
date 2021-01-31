package com.douglei.tools.file.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.CloseUtil;
import com.douglei.tools.ExceptionUtil;

/**
 * 
 * @author DougLei
 */
public class FileBufferedWriter implements AutoCloseable {
	private static final Logger logger = LoggerFactory.getLogger(FileBufferedWriter.class);
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
	
	/**
	 * 获取写出的目标文件实例
	 * @return
	 */
	public File getTargetFile() {
		return targetFile;
	}
	
	public BufferedWriter getWriter() throws IOException {
		if(writer == null) {
			if(!targetFile.exists()) {
				File folder = targetFile.getParentFile();
				if(!folder.exists()) 
					folder.mkdirs();
				targetFile.createNewFile();
			}
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, append), charset));
		}
		return writer;
	}
	public void newLine() {
		try {
			getWriter().newLine();
		} catch (IOException e) {
			logger.error("newLine时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	public void write(char c) {
		try {
			getWriter().write(c);
		} catch (IOException e) {
			logger.error("write时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	public void write(String str) {
		try {
			getWriter().write(str);
		} catch (IOException e) {
			logger.error("write时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	public void writeln(String str) {
		try {
			getWriter().write(str);
			getWriter().newLine();
		} catch (IOException e) {
			logger.error("writeln时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
	public void writeAndClose(String str) {
		try {
			getWriter().write(str);
		} catch (IOException e) {
			logger.error("writeAndClose时出现异常: {}", ExceptionUtil.getStackTrace(e));
		} finally {
			close();
		}
	}
	
	@Override
	public void close() {
		try {
			writer.close();
			writer = null;
		} catch (IOException e) {
			logger.error("关闭时出现异常: {}", ExceptionUtil.getStackTrace(e));
		}
	}
}
