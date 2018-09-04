package com.hong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hong.bean.Movie;
import com.hong.bean.Page;
import com.hong.utils.DaoUtil;

public class MovieDao {

	private Connection con = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;

	public boolean add(Movie movie){
		
		con = DaoUtil.getConn();
		String sql = "insert into movie(id,mvName,mvDesc,mvDuration,uploader,uploadTime,"
				+ "playTimes,isEnabled,label,goodCount,category,costPoint,extName,"
				+ "imgExtName,createDate, lastEditDate) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, movie.getId());
			state.setString(2, movie.getMvName());
			state.setString(3, movie.getMvDesc());
			state.setString(4, movie.getMvDuration());
			state.setString(5, movie.getUploader());
			//得到首次上传的时间戳
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			state.setTimestamp(6, stamp);
			state.setInt(7, 0);
			state.setString(8, movie.getIsEnabled());
			state.setString(9, movie.getLabel());
			state.setInt(10, 0);
			state.setString(11, movie.getCategory());
			state.setInt(12, movie.getCostPoint());
			state.setString(13, movie.getExtName());
			state.setString(14, movie.getImgExtName());
			state.setTimestamp(15, stamp);
			state.setTimestamp(16, stamp);
			
			
			int row = state.executeUpdate();
			if(row>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return false;
	}
	
	public List<Movie> getMovieByUploader(String userId){
			
			con = DaoUtil.getConn();
			String sql = "select * from movie where uploader=?;";
			try {
				state = con.prepareStatement(sql );
				state.setString(1, userId);
				rs = state.executeQuery();
				List<Movie> list = new ArrayList<Movie>();
				/*
							String id;String mvName;String mvDesc;
							String mvDuration;String uploader;Timestamp uploadTime;
							int playTimes; String isEnabled; String label;
							int goodCount; String category; int costPoint;
							String extName; String imgExtName; Timestamp createDate;
							Timestamp lastEditDate;
				 */
				while(rs.next()){
					list.add(new Movie(
							rs.getString("id"),
							rs.getString("mvName"),
							rs.getString("mvDesc"),
							rs.getString("mvDuration"),
							userId,
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
	public Movie getMovieById(String id){
		
		con = DaoUtil.getConn();
		String sql = "select * from movie where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, id);
			rs = state.executeQuery();
			/*
							String id;String mvName;String mvDesc;
							String mvDuration;String uploader;Timestamp uploadTime;
							int playTimes; String isEnabled; String label;
							int goodCount; String category; int costPoint;
							String extName; String imgExtName; Timestamp createDate;
							Timestamp lastEditDate;
			 */
			Movie movie = null;
			if(rs.next())
			movie =  new Movie(
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
						);
			
			
			return movie;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return null;
	}
	public boolean update(Movie movie, String id){
		
		con = DaoUtil.getConn();
		String sql = "update movie set mvName=?,mvDesc=?,category=?,mvDuration=?,label=? where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, movie.getMvName());
			state.setString(2, movie.getMvDesc());
			state.setString(3, movie.getCategory());
			state.setString(4, movie.getMvDuration());
			state.setString(5, movie.getLabel());
			state.setString(6, id);
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
	public boolean updateIsEnabled(String id, String isEnabled){
		//改变电影播放的权限
		//11才可以进行播放
		con = DaoUtil.getConn();
		String sql = "update movie set isEnabled=? where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, isEnabled);
			state.setString(2,id);
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
	//通过id删除电影的记录
	public boolean delete(String id){
		con = DaoUtil.getConn();
		String sql = "delete from movie where id=?;";
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

	public List<Movie> getMovieByUploader(String userId, Page<Movie> page) {
		con = DaoUtil.getConn();
		String sql = "select * from movie where uploader=? limit ?,?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, userId);
			int cur = page.getPage();
			int start = (cur-1)*3;
			int num = 3;
			if(cur == page.getTotalPage())
				num = (cur*3>page.getTotalRecs())?page.getTotalRecs()-(cur-1)*3:3;
			state.setInt(2, start);
			state.setInt(3, num);
			rs = state.executeQuery();
			List<Movie> list = new ArrayList<Movie>();
			
			while(rs.next()){
				list.add(new Movie(
						rs.getString("id"),
						rs.getString("mvName"),
						rs.getString("mvDesc"),
						rs.getString("mvDuration"),
						userId,
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

	public List<Movie> search(String userId, String keyWord) {
		con = DaoUtil.getConn();
		String sql = "select * from movie where uploader=? and mvName like '%"+keyWord+"%';";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, userId);
			rs = state.executeQuery();
			List<Movie> list = new ArrayList<Movie>();
			/*
						String id;String mvName;String mvDesc;
						String mvDuration;String uploader;Timestamp uploadTime;
						int playTimes; String isEnabled; String label;
						int goodCount; String category; int costPoint;
						String extName; String imgExtName; Timestamp createDate;
						Timestamp lastEditDate;
			 */
			while(rs.next()){
				list.add(new Movie(
						rs.getString("id"),
						rs.getString("mvName"),
						rs.getString("mvDesc"),
						rs.getString("mvDuration"),
						userId,
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


}

