package net.onest.servlet.background.product;

import net.onest.bean.Book;
import net.onest.bean.BookType;
import net.onest.dao.BookDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebServlet("/admin/add-product.do")
@MultipartConfig
public class AddProductServlet extends HttpServlet {

	// 文件URI
	static final private String FILE_URI = "/foreground/images/product/";
	// 文件上传位置
	static final private String FILE_UPLOAD_PATH = "/Users/lelecat/Documents/Projects/Java/ShoppingCart/web/foreground/images/product/";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		Map<String, String[]> params = req.getParameterMap();

		String type = params.get("type")[0];

		if (type.equals("book")) {

			String bookName = params.get("book_name")[0];
			String bookAuthor = params.get("book_author")[0];
			String bookPublisher = params.get("book_publisher")[0];
			String bookPublishData = params.get("book_publish_data")[0];
			String strBookPrice = params.get("book_price")[0];
			double bookPrice = strBookPrice.equals("") ? 0 : Double.parseDouble(strBookPrice);
			int bookTypeId = Integer.parseInt(params.get("book_type")[0]);
			BookType bookType = BookDao.getBookTypeById(bookTypeId);

			// 图片
			Part file = req.getPart("book_image");
			String fileType = file.getHeader("content-disposition");

			Book book = new Book();
			book.setName(bookName);
			book.setType(bookType);
			book.setAuthor(bookAuthor);
			book.setPublisher(bookPublisher);
			book.setPublishData(bookPublishData);
			book.setPrice(bookPrice);

			if (file.getSize() > 0) {
				// 如果上传了图片
				fileType = fileType.substring(fileType.lastIndexOf("."), fileType.length() - 1);
				book.setImageUrl(FILE_URI + "book_?" + fileType);
			}
			int bookId = BookDao.createBook(book);
			if (file.getSize() > 0) {
				// 将图片上传到服务器
				file.write(FILE_UPLOAD_PATH + "book_" + bookId + fileType);
			}

			if (bookId > 0) {
				req.setAttribute("result", "添加成功!");
				req.getRequestDispatcher("/admin/add-product.jsp").forward(req, resp);
			} else {
				req.setAttribute("result", "添加失败!");

				req.setAttribute("book_name", bookName);
				req.setAttribute("book_author", bookAuthor);
				req.setAttribute("book_publisher", bookPublishData);
				req.setAttribute("book_publish_data", bookPublishData);
				req.setAttribute("book_price", bookPrice);
				req.setAttribute("book_type", bookTypeId);

				req.getRequestDispatcher("/admin/add-product.jsp").forward(req, resp);
			}

		}

	}
}
