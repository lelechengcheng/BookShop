package net.lele.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@Entity
@Table(name = "Users")
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private String address;
	private Timestamp postTime;

	private Set<Order> orders = new HashSet<>();

	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinTable(name = "user2order",
			joinColumns = @JoinColumn(name = "user_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "order_id"
					, referencedColumnName = "id")
	)
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
