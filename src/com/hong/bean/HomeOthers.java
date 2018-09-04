package com.hong.bean;

import java.sql.Timestamp;

public class HomeOthers {

	private String id;
	private String url;
	private String img;
	private String category;
	private String creator;
	private Timestamp createDate;
	public HomeOthers() {
		super();
	}
	public HomeOthers(String id, String url, String img, String category,
			String creator, Timestamp createDate) {
		super();
		this.id = id;
		this.url = url;
		this.img = img;
		this.category = category;
		this.creator = creator;
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "HomeOthers [id=" + id + ", url=" + url + ", img=" + img
				+ ", category=" + category + ", creator=" + creator
				+ ", createDate=" + createDate + "]";
	}
	
}
