package com.edu.vo;

public class Teacher extends User{
	private String subject;

	public Teacher(int id, String name, String email, String subject) {
		super(id, name, email);
		this.subject = subject;
	}
	
	public Teacher(String name, String email, String subject) {
		super(name, email);
		this.subject = subject;
	}
	
	public Teacher(String name, String subject) {
		super(name);
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return super.toString() + "Teacher [subject=" + subject + "]";
	}

}
