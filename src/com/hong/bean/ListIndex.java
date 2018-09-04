package com.hong.bean;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ListIndex {

	private String id;
	private String listName;
	private String creator;
	private Timestamp createDate;
	private List<PlayList> list = new LinkedList<PlayList>();
	public void add(PlayList playList){
		list.add(playList);
	}
	public ListIndex() {
		super();
	}
	public ListIndex(String id, String listName, String creator,
			Timestamp createDate) {
		super();
		this.id = id;
		this.listName = listName;
		this.creator = creator;
		this.createDate = createDate;
	}
	public int getListNum() {
		return list.size();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
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
	
	public List<PlayList> getList() {
		return list;
	}
	public void setList(List<PlayList> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ListIndex [id=" + id + ", listName=" + listName + ", creator="
				+ creator + ", createDate=" + createDate + ", list=" + list
				+ "]";
	}
	
	
	
}
