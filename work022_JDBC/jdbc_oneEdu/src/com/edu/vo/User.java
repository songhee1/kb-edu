package com.edu.vo;

public class User {
	private int id;
	private String name;
	private String email;
	
	public User() {
		System.out.println(getClass().getName()+"부모 생성자...");
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public User(int id, String name, String email) {
		this(id, name);
		this.email = email;
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	
	public User(String name) {
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void changeEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
