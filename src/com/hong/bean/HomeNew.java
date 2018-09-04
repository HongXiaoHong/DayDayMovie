package com.hong.bean;

import java.sql.Timestamp;

public class HomeNew {

	private String id;
	private String url;
	private String img;
	private String creator;
	private Timestamp createDate;
	public HomeNew() {
		super();
	}
	public HomeNew(String id, String url, String img, String creator,
			Timestamp createDate) {
		super();
		this.id = id;
		this.url = url;
		this.img = img;
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
		return "HomeNew [id=" + id + ", url=" + url + ", img=" + img
				+ ", creator=" + creator + ", createDate=" + createDate + "]";
	}
	
}
