package com.douglei.tools.scanner;

/**
 * 
 * @author DougLei
 */
public class ScannerException extends RuntimeException{
	private static final long serialVersionUID = -4914858900534711783L;

	public ScannerException(String message) {
		super(message);
	}

	public ScannerException(String message, Throwable cause) {
		super(message, cause);
	}
}
