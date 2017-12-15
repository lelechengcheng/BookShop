package net.lele.controller.foreground;

import net.lele.entity.Book;
import net.lele.entity.Order;
import net.lele.entity.User;
import net.lele.service.BookService;
import net.lele.service.CartService;
import net.lele.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class CartController {

	@Resource
	private CartService cartService;
	@Resource
	private OrderService orderService;

	@RequestMapping(value = "/add_to_cart.do")
	public String addToCart(
			@RequestParam("book_id") int bookId,
			@RequestParam(value = "cart_page", required = false) Boolean isCartPage,
			HttpServletRequest request
	) {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		Order cart = (Order) session.getAttribute("cart");

		if (user == null) {
			// 若未登录, 跳至登录页面
			return "redirect:/login";
		}

		if (cart == null) {
			// 若没有购物车对象, 则新建
			cart = new Order();
			cart.setUser(user);
			session.setAttribute("cart", cart);
		}

		this.cartService.addBookToCart(bookId, cart);

		// 跳转
		isCartPage = isCartPage == null ? false : isCartPage;
		if (isCartPage) {
			return "redirect:/cart";
		} else {
			return "redirect:/book?book_id=" + bookId;
		}
	}

	@RequestMapping(value = "/order.do")
	public String order(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Order order = (Order) session.getAttribute("cart");
		if (order != null) {
			if (order.getUser() == null) {
				order.setUser(user);
			}
			this.orderService.createOrder(order);
			session.removeAttribute("cart");
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = "/remove_from_cart.do")
	public String deleteFromCart(
			@RequestParam("book_id") int bookId,
			HttpServletRequest request
	) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Order cart = (Order) session.getAttribute("cart");

		if (user == null) {
			// 若未登录, 跳至登录页面
			return "redirect:/login";
		}

		if (cart == null) {
			// 若没有购物车对象, 则新建
			cart = new Order();
			cart.setUser(user);
		}

		this.cartService.deleteBookFromCart(bookId, cart);

		return "redirect:/cart";

	}

}
