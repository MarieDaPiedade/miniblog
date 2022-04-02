package com.bge.blog.exception;

import java.util.Map;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	public UserAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public UserAlreadyExistsException(Map<String, String> s) {
		super(UserAlreadyExistsException.convert(s));
	}

	private static String convert(Map<String,String> s) {
		StringBuilder res = new StringBuilder(); 
		for(Map.Entry<String,String> se : s.entrySet()) {
			res.append(se.getKey() + " : " + se.getValue() + "\n");
		}
		return res.toString();
		
	}
	
}
