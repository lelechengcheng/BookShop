package net.onest.servlet.background.page;

import net.onest.bean.Order;
import net.onest.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebServlet("/admin/order-detail.jsp")
public class OrderDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String strId = req.getParameter("order_id");
		int id = strId == null || strId.equals("") ? -1 : Integer.parseInt(strId);
		Order order = OrderDao.getOrderById(id);

		req.setAttribute("order", order);
		req.getRequestDispatcher("/background/order-detail-module.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
