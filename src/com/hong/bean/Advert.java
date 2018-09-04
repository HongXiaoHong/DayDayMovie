package com.hong.bean;

import java.sql.Timestamp;

public class Advert {
	private String id;
	private String imgpath;
	private String url;
	private Timestamp createDate;
	public Advert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Advert(String id, String imgpath, String url, Timestamp createDate) {
		super();
		this.id = id;
		this.imgpath = imgpath;
		this.url = url;
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Advert [id=" + id + ", imgpath=" + imgpath + ", url=" + url
				+ ", createDate=" + createDate + "]";
	}
	
}
