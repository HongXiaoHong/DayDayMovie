package com.hong.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DaoUtil {

	public static Connection getConn() {
		Map<String, String> conInfo = GetPropertiesUtil.getConInfo("dao.properties");
		Connection con = null;
		try {
			Class.forName(conInfo.get("driver"));
			con = DriverManager.getConnection(conInfo.get("url"),
					conInfo.get("user"),conInfo.get("password"));
			System.out.println("con"+con);
			System.out.printf("连接数据库%s成功",conInfo.get("url"));
		} catch (ClassNotFoundException e) {
			System.out.println("没有找到类，有可能时没有导包");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return con;
	}
	public static void release(ResultSet rs, Statement state, Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				rs=null;
			}
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				state=null;
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				con=null;
			}
		}
	}
}
