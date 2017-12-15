package net.lele.controller.background;

import net.lele.entity.Order;
import net.lele.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/order")
public class OrderController {
	@Resource
	private OrderService orderService;

	@RequestMapping(value="delete.do")
	public String delete(
			@RequestParam("id") Integer orderId
	){
		if (orderId > 0) {
			this.orderService.deleteOrder(orderId);
		}

		return "redirect:/admin/order/list";
	}

	@RequestMapping(value="detail")
	public String detail(
			@RequestParam("id") Integer orderId,
			HttpServletRequest request
	){
		Order order = this.orderService.getOrderById(orderId);

		request.setAttribute("order", order);
		return "/background/order-detail-module";
	}

	@RequestMapping(value="list")
	public String list(HttpServletRequest request){
		List<Order> orders = this.orderService.getAllBriefOrders();
		request.setAttribute("orders", orders);

		return "/background/list-order-module";
	}
}
