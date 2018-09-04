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
import com.hong.bean.PlayList;
import com.hong.utils.DaoUtil;

public class PlayListDao {

	private Connection con = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;

	public boolean add(PlayList playList){
		/*
		private String id;
		private String listId;
		private int itemNo;
		private String movieId;
		private String creator;
		private Timestamp createDate;
		 */
		con = DaoUtil.getConn();
		String sql = "insert into playlist(id,listId,itemNo,movieId,creator,createDate) "
				+ "values(?,?,?,?,?,?);";
		try {
			
			state = con.prepareStatement(sql );
			state.setString(1, playList.getId());
			state.setString(2, playList.getListId());
			state.setInt(3, 1);
			state.setString(4, playList.getMovieId());
			state.setString(5, playList.getCreator());
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			state.setTimestamp(6, stamp);
			
			
			state.executeUpdate();
			
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return false;
	}
	
	public boolean addPlayList(List<PlayList> list){
		boolean ret = false;
		for(PlayList playlist: list){
			ret = add(playlist);
		}
		return ret;
	}
	
	public List<PlayList> getPlayList(String listId){
		
		con = DaoUtil.getConn();
		String sql = "select * from playlist where listId=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, listId);
			rs = state.executeQuery();
			List<PlayList> list = new ArrayList<PlayList>();
			/*
							private String id;
							private String listId;
							private int itemNo;
							private String movieId;
							private String creator;
							private Timestamp createDate;
			 */
			while(rs.next()){
				list.add(new PlayList(
						rs.getString("id"),
						rs.getString("listId"),
						rs.getInt("itemNo"),
						rs.getString("movieId"),
						rs.getString("creator"),
						rs.getTimestamp("createDate")
						));
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return null;
	}
	public List<Movie> getMovieList(String listId){
		
		con = DaoUtil.getConn();
		String sql = "select m.id,mvName,mvDesc,mvDuration,uploader,uploadTime,playTimes,isEnabled,label,goodCount,category,costPoint,extName,imgExtName,m.createDate,lastEditDate from playlist p,movie m where p.movieId=m.id and p.listId=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, listId);
			rs = state.executeQuery();
			List<Movie> list = new ArrayList<Movie>();
			/*
							private String id;
							private String listId;
							private int itemNo;
							private String movieId;
							private String creator;
							private Timestamp createDate;
			 */
			while(rs.next()){
				list.add(new Movie(
						rs.getString("id"),
						rs.getString("mvName"),
						rs.getString("mvDesc"),
						rs.getString("mvDuration"),
						rs.getString("uploader"),
						rs.getTimestamp("uploadTime"),
						rs.getInt("playTimes"),
						rs.getString("isEnabled"),
						rs.getString("label"),
						rs.getInt("goodCount"),
						rs.getString("category"),
						rs.getInt("costPoint"),
						rs.getString("extName"),
						rs.getString("imgExtName"),
						rs.getTimestamp("createDate"),
						rs.getTimestamp("lastEditDate")
						));
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return null;
	}
	
	public boolean deleteList(String listId, String creator, List<String> list){
		boolean ret = false;
		for(String movieId: list){
			ret = deleteByOtherWay(listId, movieId, creator);
		}
		return ret;
	}
	//通过另一种主键的方式删除列表中电影的记录
	public boolean deleteByOtherWay(String listId, String movieId, String creator){
		con = DaoUtil.getConn();
		String sql = 
				"delete from playlist where listId=?"
				+ " and movieId=? and creator=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, listId);
			state.setString(2, movieId);
			state.setString(3, creator);
			
			state.executeUpdate();
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
		String sql = "delete from playlist where id=?;";
		try {
			state = con.prepareStatement(sql );
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

}

