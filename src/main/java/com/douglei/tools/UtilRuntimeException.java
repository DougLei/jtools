package com.douglei.tools;

/**
 * 
 * @author DougLei
 */
public class UtilRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 2948717052707383884L;
	
	public UtilRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	public UtilRuntimeException(Throwable cause) {
		super(cause);
	}
	public UtilRuntimeException(String message) {
		super(message);
	}
}
