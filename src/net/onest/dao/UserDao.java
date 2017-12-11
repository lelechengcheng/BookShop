package net.onest.dao;

import net.onest.bean.Admin;
import net.onest.bean.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
public class UserDao {

	private static User getUserByResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("user_id"));
		user.setUsername(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("user_passwd"));
		user.setEmail(resultSet.getString("user_email"));
		user.setTelephone(resultSet.getString("user_telephone"));
		user.setAddress(resultSet.getString("user_address"));
		return user;
	}

	public static boolean isUsernameExisted(String username) {
		boolean isExisted = true;
		String sql = "select * from Users where user_name=?";

		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setString(1, username);
			ResultSet resultSet = preStatement.executeQuery();

			if (!resultSet.next()) {
				isExisted = false;
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return isExisted;
	}

	public static boolean createUser(String username, String password, String email, String telephone, String address) {

		String sqlCreateUser = "insert into Users (user_name, user_passwd, user_email, user_telephone, user_address) values(?, ?, ?, ?, ?)";

		try {
			// 添加订单
			PreparedStatement psOrder = MySqlConnection.getConnection().prepareStatement(sqlCreateUser);
			psOrder.setString(1, username);
			psOrder.setString(2, password);
			psOrder.setString(3, email);
			psOrder.setString(4, telephone);
			psOrder.setString(5, address);
			psOrder.executeUpdate();
			psOrder.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static User getUser(String username, String password) {

		User user = null;
		String sql = "select * from Users where user_name=? and user_passwd=?";

		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setString(1, username);
			preStatement.setString(2, password);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				user = UserDao.getUserByResultSet(resultSet);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static User getUserById(int id) {
		User user = null;
		String sql = "select * from Users where user_id=?";

		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setInt(1, id);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				user = UserDao.getUserByResultSet(resultSet);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return user;
	}

	public static Admin getAdmin(String username, String password) {
		Admin admin = null;
		String sql = "select * from Admin where admin_name=? and admin_passwd=?";

		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setString(1, username);
			preStatement.setString(2, password);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				admin = new Admin();
				admin.setId(resultSet.getInt("admin_id"));
				admin.setUsername(resultSet.getString("admin_name"));
				admin.setPassword(resultSet.getString("admin_passwd"));
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return admin;
	}
}
