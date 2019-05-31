package com.douglei.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关闭连接、流的工具类
 * @author DougLei
 */
public class CloseUtil {
	private static final Logger logger = LoggerFactory.getLogger(CloseUtil.class);
	
	/**
	 * 关闭File-IO类
	 * @param ios
	 */
	public static void closeIO(Object... ios){
		if(ios == null || ios.length == 0){
			logger.debug("要关闭的ios对象集合为空");
			return;
		}
		for (Object io : ios) {
			closeIO(io);
		}
	}
	
	/**
	 * 关闭数据库链接类
	 * @param dbconns
	 */
	public static void closeDBConn(Object... dbconns){
		if(dbconns == null || dbconns.length == 0){
			logger.debug("要关闭的dbconns对象集合为空");
			return;
		}
		for (Object dbconn : dbconns) {
			closeDBConn(dbconn);
		}
	}
	
	/**
	 * 关闭File-IO类
	 * @param io
	 */
	private static void closeIO(Object io){
		if(io == null){
			logger.debug("要关闭的io对象为空");
			return;
		}

		String ioClass = io.getClass().toString();
		logger.debug("要关闭的io对象为：{}", ioClass);
		try {
			if(ioClass.contains("Writer")){
				Writer writer = (Writer) io;
				writer.flush();
				writer.close();
			}else if(ioClass.contains("Reader")){
				Reader reader = (Reader) io;
				reader.close();
			}else if(ioClass.contains("OutputStream")){
				OutputStream out = (OutputStream) io;
				out.flush();
				out.close();
			}else if(ioClass.contains("InputStream")){
				InputStream in = (InputStream) io;
				in.close();
			}else{
				logger.debug("没有匹配到名为[{}]的io对象", ioClass);
			}
		} catch (IOException e) {
			throw new RuntimeException("关闭io对象["+ioClass+"]时, 出现异常", e);
		} finally {
			io = null;
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param dbconn
	 */
	private static void closeDBConn(Object dbconn){
		if(dbconn == null){
			logger.debug("要关闭的dbconn对象为空");
			return;
		}

		String dbconnClass = dbconn.getClass().toString();
		logger.debug("要关闭的dbconn对象为：{}", dbconnClass);
		try {
			if(dbconnClass.contains("Connection")){
				Connection conn = (Connection) dbconn;
				conn.close();
			}else if(dbconnClass.contains("Statement")){
				Statement st = (Statement) dbconn;
				st.close();
			}else if(dbconnClass.contains("ResultSet")){
				ResultSet rs = (ResultSet) dbconn;
				rs.close();
			}else{
				logger.debug("没有匹配到名为[{}]的dbconn对象", dbconnClass);
			}
		} catch (SQLException e) {
			throw new RuntimeException("关闭dbconn对象["+dbconnClass+"]时, 出现异常", e);
		} finally {
			dbconn = null;
		}
	}
}
