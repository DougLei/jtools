package com.douglei.tools.utils.exception;

import com.douglei.tools.utils.UtilException;

/**
 * 
 * @author DougLei
 */
public class MultiException extends UtilException {
	private static final long serialVersionUID = 5234482096150375397L;

	public MultiException(String message, Throwable t) {
		super(message, t);
	}
}
