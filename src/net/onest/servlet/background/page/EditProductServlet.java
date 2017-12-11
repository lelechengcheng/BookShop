package net.onest.servlet.background.page;

import net.onest.bean.Book;
import net.onest.bean.BookType;
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
@WebServlet("/admin/edit-product.jsp")
public class EditProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String strBookId = req.getParameter("id");
		int bookId = strBookId.equals("") ? 0 : Integer.parseInt(strBookId);

		List<BookType> bookTypes = BookDao.getAllBookType();
		req.setAttribute("book_types", bookTypes);

		Book book = BookDao.getBookById(bookId);

		req.setAttribute("book_id", book.getId());
		req.setAttribute("book_name", book.getName());
		req.setAttribute("book_author", book.getAuthor());
		req.setAttribute("book_publisher", book.getPublisher());
		req.setAttribute("book_publish_data", book.getPublishData());
		req.setAttribute("book_price", book.getPrice());
		req.setAttribute("book_type", book.getType().getId());
		req.setAttribute("book_imgUrl", book.getImageUrl());

		req.getRequestDispatcher("/background/edit-product-module.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
