package net.onest.servlet.foreground.cart;

import net.onest.bean.Order;
import net.onest.bean.OrderDetail;
import net.onest.bean.User;
import net.onest.dao.BookDao;

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
@WebServlet("/add_to_cart.do")
public class AddToCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		int bookId = Integer.parseInt(req.getParameter("book_id"));
		boolean isCartPage = Boolean.parseBoolean(req.getParameter("cart_page"));

		User user = (User) session.getAttribute("user");
		Order cart = (Order) session.getAttribute("cart");

		if (user == null) {
			// 若未登录, 跳至登录页面
			resp.sendRedirect("/login.jsp");
			return;
		}

		if (cart == null) {
			// 若没有购物车对象, 则新建
			cart = new Order();
			cart.setUser(user);
			session.setAttribute("cart", cart);
		}

		// 浏览购物车项目,
		// 若已存在即将添加的项目, 则数量加一,
		// 若不存在即将添加的项目, 则新建购物车项目, 并添加至购物车
		boolean isAdded = false;
		for (OrderDetail item : cart.getOrderDetails()) {
			if (item.getBook().getId() == bookId) {
				item.setCount(item.getCount() + 1);
				isAdded = true;
			}
		}
		if (!isAdded) {
			OrderDetail item = new OrderDetail();
			item.setBook(BookDao.getBookById(bookId));
			item.setCount(1);
			item.setOrder(cart);
			cart.addOrderDetail(item);
		}

		// 跳转
		if (isCartPage) {
			resp.sendRedirect("/cart.jsp");
		} else {
			resp.sendRedirect("/book.jsp?book_id=" + bookId);
		}
	}
}
