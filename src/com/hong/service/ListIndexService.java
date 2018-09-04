package com.hong.service;

import java.sql.Timestamp;
import java.util.List;

import com.hong.bean.ListIndex;
import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.dao.ListIndexDao;
import com.hong.utils.UUIDUtil;

public class ListIndexService {

	private ListIndexDao dao = new ListIndexDao();
	
	
	//通过ID获取列表
	public ListIndex getListIndexById(String id){
		return dao.getListIndexById(id);
	}
	//通过ID删除数据库中的记录
	public boolean deleteById(String id){
		return dao.delete(id);
	}
	//通过全部的列表
	public List<ListIndex> getAllListIndex() {
		return dao.getListIndex();
	}
	//通过全部的列表
	public List<ListIndex> getListByPage(String creator, Page page) {
		return dao.getListByPage(creator, page);
	}
	//通过全部的列表
	public List<ListIndex> getListByCreator(String creator) {
		return dao.getListByCreator(creator);
	}
	public String add(ListIndex listIndex) {
		listIndex.setId(UUIDUtil.getUUID());
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		listIndex.setCreateDate(stamp);
		return dao.add(listIndex);
	}
	public List<ListIndex> getListByPage(String creator, Page<Movie> page,
			int num) {
		return dao.getListByPage(creator, page, num);
	}
	public List<ListIndex> search(String userId, String keyWord) {
		return dao.search(userId, keyWord);
	}
}
