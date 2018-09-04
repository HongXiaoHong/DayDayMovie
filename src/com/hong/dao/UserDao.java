package com.hong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.hong.bean.User;
import com.hong.utils.DaoUtil;
import com.hong.utils.UUIDUtil;

public class UserDao {

	private Connection con = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;
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
	public boolean add(User user){
		
		con = DaoUtil.getConn();
		String sql = "insert into user(id,name,pass,nickName,email,qq,createDate) values(?,?,?,?,?,?,?);";
		try {
			state = con.prepareStatement(sql );
			state.setString(1, UUIDUtil.getUUID());
			state.setString(2, user.getName());
			state.setString(3, user.getPass());
			state.setString(4, user.getNickName());
			state.setString(5, user.getEmail());
			state.setString(6, user.getQq());
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			state.setTimestamp(7, stamp);

/*			Field field = user.getClass().getFields()[0];
			Type genericType = field.getGenericType();
			System.out.println("genericType="+genericType);*/
			
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
}
