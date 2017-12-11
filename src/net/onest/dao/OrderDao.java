package net.onest.dao;

import net.onest.bean.Book;
import net.onest.bean.Order;
import net.onest.bean.OrderDetail;
import net.onest.bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
public class OrderDao {

	private static Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("order_id");
		User user = UserDao.getUserById(resultSet.getInt("user_id"));
		Timestamp time = resultSet.getTimestamp("order_time");

		Order order = new Order();
		order.setId(id);
		order.setUser(user);
		order.setTime(time);

		return order;
	}

	private static OrderDetail getOrderDetailFormResultSet(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("order_detail_id");
		Book book = BookDao.getBookById(resultSet.getInt("book_id"));
		int count = resultSet.getInt("count");

		OrderDetail detail = new OrderDetail();
		detail.setId(id);
		detail.setBook(book);
		detail.setCount(count);

		return detail;
	}

	public static List<Order> getAllBriefOrders() {
		List<Order> orders = new ArrayList<>();

		String sql = "select * from Orders";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preStatement.executeQuery();

			while (resultSet.next()) {
				Order order = OrderDao.getOrderFromResultSet(resultSet);
				orders.add(order);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	public static Order getOrderById(int id) {
		Order order = null;

		String sql = "select * from Orders where order_id=?";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setInt(1, id);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				order = OrderDao.getOrderFromResultSet(resultSet);
				List<OrderDetail> details = OrderDao.getAllOrderDetailsByOrder(order);
				order.setOrderDetails(details);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

	public static List<OrderDetail> getAllOrderDetailsByOrderId(int id) {
		Order order = OrderDao.getOrderById(id);
		return getAllOrderDetailsByOrder(order);
	}

	public static List<OrderDetail> getAllOrderDetailsByOrder(Order order) {

		List<OrderDetail> details = new ArrayList<>();

		if (order != null) {

			String sql = "select * from Order_Detail where order_id=?";
			try {
				PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
				preStatement.setInt(1, order.getId());
				ResultSet resultSet = preStatement.executeQuery();

				while (resultSet.next()) {
					OrderDetail detail = OrderDao.getOrderDetailFormResultSet(resultSet);
					detail.setOrder(order);
					details.add(detail);
				}

				resultSet.close();
				preStatement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return details;
	}


	public static boolean createOrder(Order order) {

		String sqlCreateDetail = "insert into Order_Detail (order_id, book_id, count) values(?, ?, ?)";
		String sqlCreateOrder = "insert into Orders (user_id) values(?)";

		try {
			// 添加订单
			PreparedStatement psOrder = MySqlConnection.getConnection().prepareStatement(sqlCreateOrder);
			psOrder.setInt(1, order.getUser().getId());
			psOrder.executeUpdate();
			ResultSet rsOrder = psOrder.getGeneratedKeys();
			if (rsOrder.next()) {
				// 添加订单明细
				List<OrderDetail> orderDetails = order.getOrderDetails();
				for (OrderDetail detail : orderDetails) {
					PreparedStatement psDetail = MySqlConnection.getConnection().prepareStatement(sqlCreateDetail);
					psDetail.setInt(1, rsOrder.getInt(1));
					psDetail.setInt(2, detail.getBook().getId());
					psDetail.setInt(3, detail.getCount());
					psDetail.executeUpdate();
					psDetail.close();
				}
			}

			rsOrder.close();
			psOrder.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteOrderById(int order_id) {
		String sqlDeleteDetail = "delete from Order_Detail where order_id=?";
		String sqlDeleteOrder = "delete from Orders where order_id=?";

		try {
			// 删除订单明细
			PreparedStatement psDetail = MySqlConnection.getConnection().prepareStatement(sqlDeleteDetail);
			psDetail.setInt(1, order_id);
			psDetail.executeUpdate();
			psDetail.close();

			// 删除订单
			PreparedStatement psOrder = MySqlConnection.getConnection().prepareStatement(sqlDeleteOrder);
			psOrder.setInt(1, order_id);
			psOrder.executeUpdate();
			psOrder.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteOrderDetailsByBookId(int id) {
		String sqlDeleteDetail = "delete from Order_Detail where book_id=?";

		try {
			// 删除订单明细
			PreparedStatement psDetail = MySqlConnection.getConnection().prepareStatement(sqlDeleteDetail);
			psDetail.setInt(1, id);
			psDetail.executeUpdate();
			psDetail.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
