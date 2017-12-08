package net.onest.bean;

import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
public class OrderDetail {
	private int id;
	private Order order;
	private Book book;
	private int count;

	public OrderDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
