package net.onest.servlet.background.product;

import net.onest.bean.Book;
import net.onest.dao.BookDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebServlet("/admin/search-product.do")
public class SearchProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String searchText = req.getParameter("search");
		List<Book> books = BookDao.getBooksLikeName(searchText);
		req.setAttribute("products", books);

		req.getRequestDispatcher("/background/search-product-module.jsp").forward(req, resp);
	}
}
