package com.hong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hong.bean.Category;
import com.hong.utils.DaoUtil;

public class CategoryDao {

	private Connection con = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;

	public List<Category> selectAll(){
		
		con = DaoUtil.getConn();
		String sql = "select * from category;";
		try {
			state = con.prepareStatement(sql );
			rs = state.executeQuery();
			List<Category> list = new ArrayList<Category>();
			while(rs.next()){
				list.add(new Category(
						rs.getString("id"),
						rs.getString("categoryName")
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

/*

	public boolean update(User user) {
		con = DaoUtil.getConn();
		String sql = "update user set nickName=?,email=?,qq=?,pass=? where id=?;";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, user.getNickName());
			state.setString(2, user.getEmail());
			state.setString(3, user.getQq());
			state.setString(4, user.getPass());
			state.setString(5, user.getId());
			
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

 */
/*
	public boolean query(User user){
		
		con = DaoUtil.getConn();
		String sql = "select * from user where name=? and pass=?;";
		try {
			state = con.prepareStatement(sql);
			state.setString(1, user.getName());
			state.setString(2, user.getPass());
			rs = state.executeQuery();
			if(rs.next()){
				//查询得到数据之后将数据库中的值设置到原来的user中
				user.setId(rs.getString("id"));
				user.setNickName(rs.getString("nickName"));
				user.setEmail(rs.getString("email"));
				user.setQq(rs.getString("qq"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DaoUtil.release(rs, state, con);
		}
		
		return false;
		
	}
*/