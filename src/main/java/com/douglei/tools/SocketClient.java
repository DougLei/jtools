package com.douglei.tools;

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
	
	private String serverHost;
	private int serverPort;
	
	public SocketClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	
	public void sendMessage(String message) {
		try {
			socket = new Socket(serverHost, serverPort);
			os = socket.getOutputStream();
			os.write(formatMessage(message));
			os.flush();
		} catch (IOException e) {
			logger.error("socket连接发送消息时出现异常: {}", ExceptionUtil.getExceptionDetailMessage(e));
		} finally {
			close();
		}
	}
	
	private byte[] formatMessage(String message) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<message.length();i++){
			sb.append(" ").append(Integer.toHexString(message.charAt(i)));
		}
		return sb.substring(1).getBytes();
//		return message.getBytes();
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
	
	public static void main(String[] args) {
		SocketClient client = new SocketClient("192.168.1.101", 504);
		client.sendMessage("close");
	}
}
