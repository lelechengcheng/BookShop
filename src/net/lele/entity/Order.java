package net.lele.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@Entity
@Table(name = "Orders")
public class Order {
	private int id;
	private User user;
	private Timestamp time;
	private List<OrderDetail> orderDetails = new ArrayList<>();

	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = User.class)
	@JoinTable(name = "user2order",
			joinColumns = @JoinColumn(name = "order_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"
					, referencedColumnName = "id")
	)
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

	@OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
	@JoinTable(name = "order2detail",
			joinColumns = @JoinColumn(name = "order_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "detail_id"
					, referencedColumnName = "id")
	)
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
