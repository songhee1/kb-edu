package com.edu.exception;

public class IdNotFoundException extends Exception{
	public IdNotFoundException() {
		super("IdNotFoundException...");
	}
	
	public IdNotFoundException(String s) {
		super(s);
	}
}
