package com.douglei.tools.instances.socket.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.douglei.tools.utils.ExceptionUtil;


public class SocketClient {
	private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
	
	private Socket socket;
	private OutputStream os;
	
	private String host;
	private int port;
	private SocketClientMessageFormat format;
	
	public SocketClient(String host, int port, SocketClientMessageFormat format) {
		this.host = host;
		this.port = port;
		this.format = format;
	}
	
	public void sendMessage(String message) {
		try {
			socket = new Socket(host, port);
			os = socket.getOutputStream();
			os.write(format.format(message));
			os.flush();
		} catch (IOException e) {
			logger.error("socket连接发送消息时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
		} finally {
			close();
		}
	}
	
	private void close() {
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				logger.error("在关闭OutputStream时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			}
			os = null;
		}
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				logger.error("在关闭socket连接时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
			}
			socket = null;
		}
	}
}
