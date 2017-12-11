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
@WebServlet("/admin/edit-product.do")
@MultipartConfig
public class EditProductServlet extends HttpServlet {

	// 文件URI
	static final private String FILE_URI = "/foreground/images/product/";
	// 文件上传位置
	static final private String FILE_UPLOAD_PATH = "/Users/lelecat/Documents/Projects/Java/ShoppingCart/web/foreground/images/product/";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Map<String, String[]> params = req.getParameterMap();

		String strId = params.get("id")[0];
		int id = strId.equals("") ? 0 : Integer.parseInt(strId);

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
			// 图片文件域
			Part file = req.getPart("book_image");

			Book book = BookDao.getBookById(id);
			book.setName(bookName);
			book.setType(bookType);
			book.setAuthor(bookAuthor);
			book.setPublisher(bookPublisher);
			book.setPublishData(bookPublishData);
			book.setPrice(bookPrice);

			String fileType = file.getHeader("content-disposition");
			if (file.getSize() > 0) {
				// 若用户上传了文件
				fileType = fileType.substring(fileType.lastIndexOf("."), fileType.length() - 1);
				book.setImageUrl(FILE_URI + "book_?" + fileType);
				// 将图片上传到服务器
				file.write(FILE_UPLOAD_PATH + "book_" + id + fileType);
			}

			req.setAttribute("book_id", book.getId());
			req.setAttribute("book_name", book.getName());
			req.setAttribute("book_author", book.getAuthor());
			req.setAttribute("book_publisher", book.getPublisher());
			req.setAttribute("book_publish_data", book.getPublishData());
			req.setAttribute("book_price", book.getPrice());
			req.setAttribute("book_type", book.getType().getId());
			req.setAttribute("book_imgUrl", book.getImageUrl());

			// 更新到数据库
			if (BookDao.updateBook(book)) {
				req.setAttribute("result", "修改成功!");
			} else {
				req.setAttribute("result", "修改失败!");
			}
			req.getRequestDispatcher("/admin/edit-product.jsp?id=" + id).forward(req, resp);
		}

	}
}
