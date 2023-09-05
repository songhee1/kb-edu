package com.edu.vo;

public class Lecture {
	private int id;
	private String name;
	private String subject;
	private int likes;
	private String price;
	
	public Lecture() {}
	//DB col : lecture_id, lecture_name, subject, likes
	public Lecture(int id, String name, String subject, int likes, String price) {
		super();
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.likes = likes;
		this.price = price;
	}
	
	public Lecture(String name, String subject, int likes, String price) {
		super();
		this.name = name;
		this.subject = subject;
		this.likes = likes;
		this.price = price;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "name: " + name + ", subject: " + subject + ", likes: " + likes + ", price: " + price;
	}
}
