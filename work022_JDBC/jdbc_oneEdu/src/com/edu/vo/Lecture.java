package com.edu.vo;

public class Lecture {
	private int id;
	private String name;
	private String subject;
	private int likes;
	private String price;
	
	
	public Lecture() {
		
	}
	
	public Lecture(String name, String subject, int likes, String price) {
		this.name = name;
		this.subject = subject;
		this.likes = likes;
		this.price = price;
	}
	
	public Lecture(int lecture_id, String name, String subject, int likes, String price) {
		this(name, subject, likes, price);
		this.id = lecture_id;
	}
	
	public Lecture(String name, String subject) {
		this.name = name;
		this.subject = subject;
	}
	
	public int getLecture_id() {
		return id;
	}
	public void setLecture_id(int lecture_id) {
		this.id = lecture_id;
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
		return "Lecture [lecture_id=" + id + ", name=" + name + ", subject=" + subject + ", likes=" + likes
				+ ", price=" + price + "]";
	}
	
}
