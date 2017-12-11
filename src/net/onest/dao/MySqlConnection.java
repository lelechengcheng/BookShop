package net.onest.dao;

import java.sql.*;

/**
 * 姓名: 马佳
 * 班级: 2015级3班
 * 学号: 2014011669
 */
public class MySqlConnection {

	private static Connection conn;
	private static String url = "jdbc:mysql://127.0.0.1:3306/webshop?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static String user = "root";//访问数据库用户名
	private static String pwd = "";//访问数据库密码

	//获取数据库连接对象
	public static Connection getConnection() {
		try {
			if (null == conn) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pwd);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//判断数据库对象是否关闭连接
	public static boolean isConnectionClosed() {
		try {
			if(null == conn || conn.isClosed()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//关闭数据库连接
	public static void closeConnection() {
		try {
			if(null != conn && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
