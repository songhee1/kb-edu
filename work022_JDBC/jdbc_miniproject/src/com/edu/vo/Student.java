package com.edu.vo;

public class Student extends User{
	private String address;
	private String phone;
	
	public Student() {
		super();
	}
	
	public Student(String address, String phone) {
		this.address = address;
		this.phone = phone;
	}
	
	public Student(int id, String name, String email, String address, String phone) {
		super(id, name, email);
		this.address = address;
		this.phone = phone;
	}
	
	public Student(String name, String email, String address, String phone) {
		super(name, email);
		this.address = address;
		this.phone = phone;
	}
	
	public Student(String name, String email, String phone) {
		super(name, email);
		this.phone = phone;
	}
	
	public Student(String name) {
		super(name);
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return super.toString() + "Student [address=" + address + ", phone=" + phone + "]";
	}
	
	
	
}
