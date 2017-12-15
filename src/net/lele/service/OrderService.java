package net.lele.service;

import net.lele.dao.OrderDao;
import net.lele.entity.Order;
import net.lele.entity.OrderDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {

	@Resource
	private OrderDao orderDao;

	public void createOrder(Order order) {

		System.out.println("=======detail========");
		for (OrderDetail detail : order.getOrderDetails()) {
			System.out.println(detail.getCount());
		}
		System.out.println("=======end========");
		this.orderDao.addOrder(order);
	}

	public void deleteOrder(Integer orderId) {
		this.orderDao.delete(this.orderDao.getOrdersById(orderId));
	}

	public List<Order> getAllBriefOrders() {
		return this.orderDao.getAllOrders();
	}

	public Order getOrderById(Integer orderId) {
		return this.orderDao.getOrdersById(orderId);
	}
}
