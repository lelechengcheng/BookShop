package net.onest.servlet.background.product;

import net.onest.dao.BookDao;

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
@WebServlet("/admin/delete-product.do")
public class DeleteProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String strId = req.getParameter("id");
		String searchPage = req.getParameter("sp");
		String searchText = req.getParameter("search");
		int id = strId == null || strId.equals("") ? -1 : Integer.parseInt(strId);

		if (id > 0) {
			BookDao.deleteBookById(id);
			if ("true".equals(searchPage)) {
				req.getRequestDispatcher("/admin/search-product.jsp?search=" + searchText).forward(req, resp);
			} else {
				req.getRequestDispatcher("/admin/list-product.jsp").forward(req, resp);
			}
		}
	}
}
