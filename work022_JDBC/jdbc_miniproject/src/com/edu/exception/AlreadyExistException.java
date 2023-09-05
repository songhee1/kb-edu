package com.edu.exception;

public class AlreadyExistException extends Exception {
	public AlreadyExistException(){
		this("이미 있는 회원이십니다. ");
	}
	public AlreadyExistException(String message){
		super(message);
	}
}