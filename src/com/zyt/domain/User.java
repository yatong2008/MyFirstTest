package com.zyt.domain;

public class User {
	private int id;
	private String username;
	private String email;
	private String grade;
	private String pwd;
	
	public User(){
	}
	
	public User(int id, String username, String email, String grade, String pwd) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.grade = grade;
		this.pwd = pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
