package com.hong.bean;

import java.util.Date;

public class User {

	private String id;
	private String name;
	private String pass;
	private String nickName;
	private String email;
	private String qq;
	
	
	public User() {
		super();
	}


	public User(String id, String name, String pass, String nickName,
			String email, String qq) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.nickName = nickName;
		this.email = email;
		this.qq = qq;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pass=" + pass
				+ ", nickName=" + nickName + ", email=" + email + ", qq=" + qq
				+ "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}
	
}
