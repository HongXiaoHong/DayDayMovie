package com.hong.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hong.bean.Movie;
import com.hong.bean.PlayList;
import com.hong.dao.PlayListDao;
import com.hong.utils.UUIDUtil;

public class PlayListService {

	private PlayListDao dao = new PlayListDao();
	public List<PlayList> getPlayListByListId(String listId){
		return dao.getPlayList(listId);
	}
	public List<Movie> getMovieListByListId(String listId){
		return dao.getMovieList(listId);
	}
	public boolean addPlayList(String listId, String userId, List<String> movieIdList){
		List<PlayList> playList = new ArrayList<PlayList>();
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		for(String movieId: movieIdList){
			playList.add(new PlayList(
					UUIDUtil.getUUID(),
					listId,
					1,
					movieId,
					userId,
					stamp
					));
		}
		return dao.addPlayList(playList);
	}
/*	//通过ID获取列表
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
	public void add(ListIndex listIndex) {
		// TODO Auto-generated method stub
		listIndex.setId(UUIDUtil.getUUID());
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		listIndex.setCreateDate(stamp);
		dao.add(listIndex);
	}*/
	public boolean deleteList(String listId, String userId,
			List<String> movieIdList) {
		return dao.deleteList(listId, userId, movieIdList);
	}
}
