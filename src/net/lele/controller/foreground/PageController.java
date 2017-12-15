package net.lele.controller.foreground;

import net.lele.entity.Book;
import net.lele.entity.User;
import net.lele.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class PageController {

	@Resource
	private BookService bookService;

	@RequestMapping("")
	public String hotBook(HttpServletRequest request) {
		List<Book> books = this.bookService.getHotBooks();
		request.setAttribute("books", books);
		request.setAttribute("tag", "热门商品");
		return "/foreground/index-module";
	}

	@RequestMapping("/new")
	public String newBook(HttpServletRequest request) {
		List<Book> books = this.bookService.getNewBooks();
		request.setAttribute("books", books);
		request.setAttribute("tag", "最新商品");
		return "/foreground/index-module";
	}

	@RequestMapping("all")
	public String allBook(HttpServletRequest request) {
		List<Book> books = this.bookService.getAllBooks();
		request.setAttribute("books", books);
		request.setAttribute("tag", "所有商品");
		return "/foreground/index-module";
	}

	@RequestMapping("book")
	public String book(
			@RequestParam("book_id") Integer bookId,
			HttpServletRequest request
	) {

		Book book = this.bookService.getBook(bookId);

		request.setAttribute("book", book);

		return "/foreground/book-module";
	}

	@RequestMapping("cart")
	public String cart(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login.jsp";
		} else {
			return "/foreground/cart-module";
		}

	}

	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		System.out.println(request.getAttribute("error"));
		return "/foreground/login-module";
	}

	@RequestMapping("register")
	public String register() {
		return "redirect:/foreground/register-module.jsp";
	}
}
