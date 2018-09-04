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
			System.out.printf("�������ݿ�%s�ɹ�",conInfo.get("url"));
		} catch (ClassNotFoundException e) {
			System.out.println("û���ҵ��࣬�п���ʱû�е���");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��");
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
