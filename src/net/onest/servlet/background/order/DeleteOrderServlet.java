package net.onest.servlet.background.order;

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
@WebServlet("/admin/delete-order.do")
public class DeleteOrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String strId = req.getParameter("id");
		int id = strId == null || strId.equals("") ? -1 : Integer.parseInt(strId);

		if (id > 0) {
			OrderDao.deleteOrderById(id);
			req.getRequestDispatcher("/admin/list-order.jsp").forward(req, resp);
		}
	}
}
