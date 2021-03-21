package com.toilatester.core.execption;

public class TestRunTimeException extends RuntimeException {
	private static final long serialVersionUID = 0L;

	public TestRunTimeException(String message) {
		super(message);
	}

	public TestRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
