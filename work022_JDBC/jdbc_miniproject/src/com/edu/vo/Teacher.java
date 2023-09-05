package com.edu.vo;

import java.util.ArrayList;


public class Teacher extends User {
	private String subject;
	
	private ArrayList<Education> educations;

	public Teacher(int id, String name, String email, String subject) {
		super(id, name, email);
		this.subject = subject;
	}

	public Teacher(String subject, ArrayList<Education> educations) {
		super();
		this.subject = subject;
		this.educations = educations;
	}

	public Teacher() {}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ArrayList<Education> getEducations() {
		return educations;
	}

	public void setEducations(ArrayList<Education> educations) {
		this.educations = educations;
	}

	@Override
	public String toString() {
		return super.toString() + ", subject: " + subject;
	}

	
}
