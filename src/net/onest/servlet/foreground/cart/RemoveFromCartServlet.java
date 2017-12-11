package net.onest.servlet.foreground.cart;

import net.onest.bean.Order;
import net.onest.bean.OrderDetail;
import net.onest.bean.User;
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
@WebServlet("/remove_from_cart.do")
public class RemoveFromCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		int bookId = Integer.parseInt(req.getParameter("book_id"));

		User user = (User) session.getAttribute("user");
		Order cart = (Order) session.getAttribute("cart");

		if (user == null) {
			// 若未登录, 跳至登录页面
			resp.sendRedirect("/login.jsp");
		}

		if (cart == null) {
			// 若没有购物车对象, 则新建
			cart = new Order();
			cart.setUser(user);
		}

		// 浏览购物车项目,
		// 若已存在即将删除的项目, 则数量加一, 不足 0 则直接从购物车移出
		for (int i = 0; i < cart.getOrderDetails().size(); i++) {
			OrderDetail item = cart.getOrderDetails().get(i);
			if (item.getBook().getId() == bookId) {
				if (item.getCount() > 1) {
					item.setCount(item.getCount() - 1);
				} else {
					cart.getOrderDetails().remove(item);
				}
			}
		}

		resp.sendRedirect("/cart.jsp");
	}
}
