package net.onest.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
public class Order {
	private int id;
	private User user;
	private Timestamp time;
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		if (this.orderDetails != null) {
			this.orderDetails.add(orderDetail);
		}
	}
}
