package com.hong.bean;

import java.sql.Timestamp;

public class Category {

	private String id;
	private String categoryName;
	private Timestamp createDate;
	public Category() {
		super();
	}
	public Category(String id, String categoryName, Timestamp createDate) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.createDate = createDate;
	}
	public Category(String id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
