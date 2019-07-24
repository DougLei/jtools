package com.douglei.tools;

/**
 * 
 * @author DougLei
 */
public abstract class RootRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 928487081008264460L;

	public RootRuntimeException() {
		super();
	}

	public RootRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RootRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RootRuntimeException(String message) {
		super(message);
	}

	public RootRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 获取异常的名称
	 * 一般指你自己项目的项目名, jar包的包名, 总之需要有一定的辨识度
	 * @return
	 */
	public abstract String getName();
}
