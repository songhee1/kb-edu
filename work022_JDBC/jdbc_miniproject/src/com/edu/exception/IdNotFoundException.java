package com.edu.exception;

public class IdNotFoundException extends Exception {
	public IdNotFoundException(){
		this("이미 있는 회원이십니다. ");
	}
	public IdNotFoundException(String message){
		super(message);
	}
}
