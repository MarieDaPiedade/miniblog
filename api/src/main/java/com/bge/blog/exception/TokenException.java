package com.bge.blog.exception;

public class TokenException extends Exception {

	private static final long serialVersionUID = 1L;

	public TokenException() {
		super();
	}

	public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

}
