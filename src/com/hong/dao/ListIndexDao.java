package com.hong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hong.bean.ListIndex;
import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.bean.PlayList;
import com.hong.utils.DaoUtil;

public class ListIndexDao {

	private Connection con = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;
	private PlayListDao playDao = new PlayListDao();
	public String add(ListIndex listIndex){
		
		con = DaoUtil.getConn();
		String sql = "insert into listindex(id,listName,creator,createDate) "
				+ "values(?,?,?,?);";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, listIndex.getId());
			state.setString(2, listIndex.getListName());
			state.setString(3, listIndex.getCreator());
			state.setTimestamp(4, listIndex.getCreateDate());
			List<PlayList> list = listIndex.getList();
			if(list!=null&&list.size()>0){
				for(PlayList playList: list)
				playDao.add(playList);
			}
			
			int row = state.executeUpdate();
			if(row>0){
				return listIndex.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return null;
	}
	
	public List<ListIndex> getListIndex(){
			
			con = DaoUtil.getConn();
			String sql = "select * from listindex;";
			try {
				state = con.prepareStatement(sql );
				rs = state.executeQuery();
				List<ListIndex> list = new ArrayList<ListIndex>();
				while(rs.next()){
					String id = rs.getString("id");
					ListIndex listIndex = new ListIndex(
							id,
							rs.getString("listName"),
							rs.getString("creator"),
							rs.getTimestamp("createDate")
							);
					list.add(listIndex);
					List<PlayList> playList = playDao.getPlayList(id);
					if(playList!=null)
					listIndex.setList(playList);
					
				}
				
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DaoUtil.release(rs, state, con);
			}
			
			return null;
		}
	public ListIndex getListIndexById(String id){
		
		con = DaoUtil.getConn();
		String sql = "select * from listindex where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, id);
			rs = state.executeQuery();
			ListIndex listIndex = null;
			if(rs.next()){
				String listId = rs.getString("id");
				listIndex =  new ListIndex(
						rs.getString("id"),
						rs.getString("listName"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						);
				List<PlayList> playList = playDao.getPlayList(listId);
				if(playList!=null)
					listIndex.setList(playList);
			}
			
			
			return listIndex;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return null;
	}
	
	public boolean update(ListIndex listIndex, String id){
		
		con = DaoUtil.getConn();
		String sql = "update listindex set listName=? where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, listIndex.getListName());
			state.setString(2, id);
			int row = state.executeUpdate();
			/*
							String id;String mvName;String mvDesc;
							String mvDuration;String uploader;Timestamp uploadTime;
							int playTimes; String isEnabled; String label;
							int goodCount; String category; int costPoint;
							String extName; String imgExtName; Timestamp createDate;
							Timestamp lastEditDate;
			 */
			
			if(row>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return false;
	}
	
	//通过id删除电影的记录
	public boolean delete(String id){
		con = DaoUtil.getConn();
		String sql = "delete from listindex where id=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, id);
			int row = state.executeUpdate();
			if(row>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return false;
	}
	//通过id得到该用户所有的记录的个数
	public int getCount(String id){
		con = DaoUtil.getConn();
		String sql = "select count(*) as count from listindex where creator=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, id);
			rs = state.executeQuery();
			if(rs.next()){
				int count = rs.getInt("count");
				return count;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return 0;
	}

	public List<ListIndex> getListByPage(String creator, Page page) {
		con = DaoUtil.getConn();
		String sql = "select count(*) as count from listindex where creator=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, creator);
			rs = state.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("count");
			}
			System.out.println("))))))))))))))))))))))))))))))))");
			System.out.println("总页数为："+count);
			page.setTotalRecs(count);
			page.calcurate();
			System.out.println("页面对象为："+page);
			System.out.println(")))))))))))))))?))))))))))))))))");
			sql = "select * from listindex where creator=? limit ?,?;";
			state = con.prepareStatement(sql );
			state.setString(1, creator);
			int cur = page.getPage();
			int start = (cur-1)*page.getRecOfPage();
			int num = page.getRecOfPage();
			if(cur==page.getTotalPage()){
				num = (cur*3>page.getTotalRecs())?page.getTotalRecs()-start:page.getRecOfPage();
			}
			state.setInt(2, start);
			state.setInt(3, num);
			rs = state.executeQuery();
			List<ListIndex> list = new ArrayList<ListIndex>();
			ListIndex listIndex = null;
			
			while(rs.next()){
				String id = rs.getString("id");
				listIndex =  new ListIndex(
						id,
						rs.getString("listName"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						);
				list.add(listIndex);
				List<PlayList> playList = playDao.getPlayList(id);
				if(playList!=null)
				listIndex.setList(playList);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return null;
	}

	public List<ListIndex> getListByPage(String creator, Page<Movie> page,
			int curPage) {
		con = DaoUtil.getConn();
		String sql = "select count(*) as count from listindex where creator=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, creator);
			rs = state.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("count");
			}
			System.out.println("))))))))))))))))))))))))))))))))");
			System.out.println("总页数为："+count);
			page.setParameters(count, curPage);
			page.calcurate();
			System.out.println("页面对象为："+page);
			System.out.println(")))))))))))))))?))))))))))))))))");
			sql = "select * from listindex where creator=? limit ?,?;";
			state = con.prepareStatement(sql );
			state.setString(1, creator);
			int cur = page.getPage();
			int start = (cur-1)*page.getRecOfPage();
			int num = page.getRecOfPage();
			if(cur==page.getTotalPage()){
				num = (cur*3>page.getTotalRecs())?page.getTotalRecs()-start:page.getRecOfPage();
			}
			state.setInt(2, start);
			state.setInt(3, num);
			rs = state.executeQuery();
			List<ListIndex> list = new ArrayList<ListIndex>();
			ListIndex listIndex = null;
			
			while(rs.next()){
				String id = rs.getString("id");
				listIndex =  new ListIndex(
						id,
						rs.getString("listName"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						);
				list.add(listIndex);
				List<PlayList> playList = playDao.getPlayList(id);
				if(playList!=null)
				listIndex.setList(playList);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return null;
	}

	public List<ListIndex> getListByCreator(String creator) {
		con = DaoUtil.getConn();
		String sql = "select * from listindex where creator=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, creator);
			rs = state.executeQuery();
			List<ListIndex> list = new ArrayList<ListIndex>();
			ListIndex listIndex = null;
			
			while(rs.next()){
				String id = rs.getString("id");
				listIndex =  new ListIndex(
						id,
						rs.getString("listName"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						);
				list.add(listIndex);
				List<PlayList> playList = playDao.getPlayList(id);
				if(playList!=null)
				listIndex.setList(playList);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return null;
	}

	public List<ListIndex> search(String userId, String keyWord) {
		con = DaoUtil.getConn();
		String sql = "select * from listindex where creator=? and listName like '%"+keyWord+"%';";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, userId);
			rs = state.executeQuery();
			List<ListIndex> list = new ArrayList<ListIndex>();
			ListIndex listIndex = null;
			
			while(rs.next()){
				String id = rs.getString("id");
				listIndex =  new ListIndex(
						id,
						rs.getString("listName"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						);
				list.add(listIndex);
				List<PlayList> playList = playDao.getPlayList(id);
				if(playList!=null)
				listIndex.setList(playList);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		return null;
	}

}

