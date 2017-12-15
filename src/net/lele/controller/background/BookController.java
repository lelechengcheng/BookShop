package net.lele.controller.background;

import net.lele.entity.Book;
import net.lele.entity.BookType;
import net.lele.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/book")
public class BookController {

	@Resource
	private BookService bookService;

	@RequestMapping(value = "add")
	public String pageAdd(HttpServletRequest request) {

		List<BookType> bookTypes = this.bookService.getAllBookTypes();
		request.setAttribute("book_types", bookTypes);

		return "/background/add-product-module";
	}

	@RequestMapping(value = "edit")
	public String pageEdit(
			@RequestParam("id") Integer bookId,
			HttpServletRequest request
	) {
		List<BookType> bookTypes = this.bookService.getAllBookTypes();
		request.setAttribute("book_types", bookTypes);

		Book book = this.bookService.getBook(bookId);

		request.setAttribute("book_id", book.getId());
		request.setAttribute("book_name", book.getName());
		request.setAttribute("book_author", book.getAuthor());
		request.setAttribute("book_publisher", book.getPublisher());
		request.setAttribute("book_publish_data", book.getPublishData());
		request.setAttribute("book_price", book.getPrice());
		request.setAttribute("book_type", book.getType().getId());
		request.setAttribute("book_imgUrl", book.getImageUrl());

		return "/background/edit-product-module";
	}

	@RequestMapping(value = "search")
	public String pageSearch() {
		return "/background/search-product-module";
	}

	@RequestMapping(value = "list")
	public String pageList(HttpServletRequest request) {
		List<Book> books = this.bookService.getAllBooks();
		request.setAttribute("products", books);

		return "/background/list-product-module";
	}

	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	public String add(
			@RequestParam("book_name") String bookName,
			@RequestParam(value = "book_author", required = false) String bookAuthor,
			@RequestParam(value = "book_publisher", required = false) String bookPublisher,
			@RequestParam(value = "book_publish_data", required = false) String bookPublishData,
			@RequestParam(value = "book_price", required = false) Double bookPrice,
			@RequestParam(value = "book_type", required = false) Integer bookTypeId,
			@RequestParam(value = "book_image", required = false) MultipartFile image,
			HttpServletRequest request
	) throws IOException {

		HttpSession session = request.getSession();
		String fileUploadPath = session.getServletContext().getRealPath("/foreground/images/product/");

		BookType bookType = this.bookService.getBookType(bookTypeId);
		Book book = new Book();
		book.setName(bookName);
		book.setType(bookType);
		book.setAuthor(bookAuthor);
		book.setPublisher(bookPublisher);
		book.setPublishData(bookPublishData);
		book.setPrice(bookPrice == null ? 0 : bookPrice);

		String fileType = "";
		if (!image.isEmpty()) {
			fileType = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
			book.setImageUrl("/foreground/images/product/" + "book_?" + fileType);
		}

		// 将添加的书籍信息同步到数据库
		int bookId = this.bookService.createBook(book);

		if (bookId > 0) {
			if (!image.isEmpty()) {
				// 上传图片
				image.transferTo(new File(fileUploadPath + "book_" + bookId + fileType));
			}
			return "redirect:/admin/book/add";
		} else {
			request.setAttribute("book_name", bookName);
			request.setAttribute("book_author", bookAuthor);
			request.setAttribute("book_publisher", bookPublishData);
			request.setAttribute("book_publish_data", bookPublishData);
			request.setAttribute("book_price", bookPrice);
			request.setAttribute("book_type", bookTypeId);

			request.setAttribute("result", "添加失败!");
		}

		return "forward:/admin/book/add";

	}

	@RequestMapping(value = "edit.do", method = RequestMethod.POST)
	public String edit(
			@RequestParam("id") Integer id,
			@RequestParam("book_name") String bookName,
			@RequestParam(value = "book_author", required = false) String bookAuthor,
			@RequestParam(value = "book_publisher", required = false) String bookPublisher,
			@RequestParam(value = "book_publish_data", required = false) String bookPublishData,
			@RequestParam(value = "book_price", required = false) Double bookPrice,
			@RequestParam(value = "book_type", required = false) Integer bookTypeId,
			@RequestParam(value = "book_image", required = false) MultipartFile image,
			HttpServletRequest request
	) throws IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String fileUploadPath = session.getServletContext().getRealPath("/foreground/images/product/");

		BookType bookType = this.bookService.getBookType(bookTypeId);
		Book book = new Book();
		book.setId(id);
		book.setName(bookName);
		book.setType(bookType);
		book.setAuthor(bookAuthor);
		book.setPublisher(bookPublisher);
		book.setPublishData(bookPublishData);
		book.setPrice(bookPrice);

		if (!image.isEmpty()) {
			// 若用户上传了文件
			String fileType = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
			book.setImageUrl("/foreground/images/product/" + "book_?" + fileType);
			// 将图片上传到服务器
			image.transferTo(new File(fileUploadPath + "book_" + id + fileType));
		}

		request.setAttribute("book_id", id);
		request.setAttribute("book_name", book.getName());
		request.setAttribute("book_author", book.getAuthor());
		request.setAttribute("book_publisher", book.getPublisher());
		request.setAttribute("book_publish_data", book.getPublishData());
		request.setAttribute("book_price", book.getPrice());
		request.setAttribute("book_type", book.getType().getId());
		request.setAttribute("book_imgUrl", book.getImageUrl());

		// 更新到数据库
		if (this.bookService.updateBook(book)) {
			request.setAttribute("result", "修改成功!");
		} else {
			request.setAttribute("result", "修改失败!");
		}

		return "forward:/admin/book/edit?id=" + id;
	}

	@RequestMapping(value = "delete.do")
	public String delete(
			@RequestParam("id") Integer id,
			@RequestParam(value = "sp", required = false) String searchPage,
			@RequestParam(value = "search", required = false) String searchText
	) {
		if (id > 0) {
			this.bookService.deleteBook(id);
			if ("true".equals(searchPage)) {
				return "forward:/admin/book/search?search=" + searchText;
			}
		}

		return "forward:/admin/book/list";
	}

	@RequestMapping(value = "search.do")
	public String search(
			@RequestParam("search") String searchText,
			HttpServletRequest request
	) {
		System.out.println("Search...");

		List<Book> books = this.bookService.getBooksLikeName(searchText);
		request.setAttribute("products", books);

		return "/background/search-product-module";
	}

}
