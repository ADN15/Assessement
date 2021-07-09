package com.example.movie.exception;

public class GlobalCustomException extends Exception {
	
	private static final long serialVersionUID = 1506365910120473080L;

    public GlobalCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalCustomException(String message) {
        super(message);
    }

}
