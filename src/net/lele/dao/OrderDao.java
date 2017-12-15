package net.lele.dao;

import net.lele.entity.Order;
import net.lele.entity.OrderDetail;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao extends BaseDao<Order, Integer> {
	public void addOrder(Order order) {
		System.out.println("前:");
		for (OrderDetail detail : order.getOrderDetails()) {
			System.out.println(detail.getCount());
		}
		System.out.println("-----------");
		super.save(order);
		System.out.println("后:");
		for (OrderDetail detail : order.getOrderDetails()) {
			System.out.println(detail.getCount());
		}
		System.out.println("-----------");
	}

	public Order getOrdersById(Integer orderId) {
		try{
			return super.findOne("from Order order where order.id=?", new Object[] {orderId});
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Order> getAllOrders() {
		try{
			return super.findByProperty("from Order", null);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
