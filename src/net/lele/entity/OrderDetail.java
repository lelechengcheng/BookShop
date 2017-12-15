package net.lele.entity;

import javax.persistence.*;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@Entity
@Table(name = "Order_Detail")
public class OrderDetail {
	private int id;
	private Order order;
	private Book book;
	private int count;

	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Order.class)
	@JoinTable(name = "order2detail",
			joinColumns = @JoinColumn(name = "detail_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "order_id"
					, referencedColumnName = "id")
	)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(targetEntity = Book.class)
	@JoinTable(name = "detail2book",
			joinColumns = @JoinColumn(name = "detail_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"
					, referencedColumnName = "id")
	)
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
