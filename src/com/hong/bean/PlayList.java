package com.hong.bean;

import java.sql.Timestamp;

public class PlayList {

	private String id;
	private String listId;
	private int itemNo;
	private String movieId;
	private String creator;
	private Timestamp createDate;
	public PlayList() {
		super();
	}
	public PlayList(String id, String listId, int itemNo, String movieId,
			String creator, Timestamp createDate) {
		super();
		this.id = id;
		this.listId = listId;
		this.itemNo = itemNo;
		this.movieId = movieId;
		this.creator = creator;
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
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
		return "PlayList [id=" + id + ", listId=" + listId + ", itemNo="
				+ itemNo + ", movieId=" + movieId + ", creator=" + creator
				+ ", createDate=" + createDate + "]";
	}
	
}
