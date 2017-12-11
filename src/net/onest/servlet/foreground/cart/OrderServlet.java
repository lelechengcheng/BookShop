package net.onest.servlet.foreground.cart;

import net.onest.bean.Order;
import net.onest.bean.User;
import net.onest.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		Order order = (Order) session.getAttribute("cart");
		if (order != null) {
			if (order.getUser() == null) {
				order.setUser(user);
			}
			OrderDao.createOrder(order);
			session.removeAttribute("cart");
		}
		resp.sendRedirect("/cart.jsp");
	}
}
