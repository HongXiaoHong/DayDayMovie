package com.hong.bean;

import java.sql.Timestamp;

public class Comment {

	private String id;
	private String movieId;
	private String content;
	private Timestamp createDate;
	private String creator;
	public Comment() {
		super();
	}
	public Comment(String id, String movieId, String content,
			Timestamp createDate, String creator) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.content = content;
		this.createDate = createDate;
		this.creator = creator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", movieId=" + movieId + ", content="
				+ content + ", createDate=" + createDate + ", creator="
				+ creator + "]";
	}
	
}
