package com.edu.exception;

public class AlreadyExistException extends Exception{
	public AlreadyExistException() {
		super("AlreadyExistException...");
	}
	
	public AlreadyExistException(String s) {
		super(s);
	}
}
