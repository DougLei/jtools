package com.douglei.tools;

/**
 * 
 * @author DougLei
 */
public class ToolsException extends RuntimeException {
	private static final long serialVersionUID = 6853162634170660728L;

	public ToolsException() {
		super();
	}

	public ToolsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ToolsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ToolsException(String message) {
		super(message);
	}

	public ToolsException(Throwable cause) {
		super(cause);
	}
}
