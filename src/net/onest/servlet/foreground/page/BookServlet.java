package net.onest.servlet.foreground.page;

import net.onest.bean.Book;
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
@WebServlet("/book.jsp")
public class BookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookId = Integer.parseInt(req.getParameter("book_id"));

		Book book = BookDao.getBookById(bookId);

		req.setAttribute("book", book);
		req.getRequestDispatcher("/foreground/book-module.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
