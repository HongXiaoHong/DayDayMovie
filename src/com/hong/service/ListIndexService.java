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
	
	
	//ͨ��ID��ȡ�б�
	public ListIndex getListIndexById(String id){
		return dao.getListIndexById(id);
	}
	//ͨ��IDɾ�����ݿ��еļ�¼
	public boolean deleteById(String id){
		return dao.delete(id);
	}
	//ͨ��ȫ�����б�
	public List<ListIndex> getAllListIndex() {
		return dao.getListIndex();
	}
	//ͨ��ȫ�����б�
	public List<ListIndex> getListByPage(String creator, Page page) {
		return dao.getListByPage(creator, page);
	}
	//ͨ��ȫ�����б�
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
