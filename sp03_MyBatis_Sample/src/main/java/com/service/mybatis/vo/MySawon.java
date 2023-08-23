package com.service.mybatis.vo;
//클래스명과 테이블명을 일치시킨다.
public class MySawon {
	private int num, age; //컬럼명과 필드명이 동일하다
	private String id, pwd, name, hiredate;
	
	//프레임워크에서는 반드시 기본 생성자가 필요...
	 public MySawon() {
		 
	 }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "MySawon [num=" + num + ", age=" + age + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", hiredate="
				+ hiredate + "]";
	}
	 
	 
}
