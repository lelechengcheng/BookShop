package net.lele.service;

import net.lele.entity.Order;
import net.lele.entity.OrderDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class CartService {


	@Resource
	private BookService bookService;

	public void addBookToCart(int bookId, Order cart) {
		// 浏览购物车项目,
		// 若已存在即将添加的项目, 则数量加一,
		// 若不存在即将添加的项目, 则新建购物车项目, 并添加至购物车
		boolean isAdded = false;
		for (OrderDetail item : cart.getOrderDetails()) {
			if (item.getBook().getId() == bookId) {
				item.setCount(item.getCount() + 1);
				isAdded = true;
			}
		}
		if (!isAdded) {
			OrderDetail item = new OrderDetail();
			item.setBook(this.bookService.getBook(bookId));
			item.setCount(1);
//			item.setOrder(cart);
			cart.getOrderDetails().add(item);
		}

	}

	public void deleteBookFromCart(int bookId, Order cart) {
		// 浏览购物车项目,
		// 若已存在即将删除的项目, 则数量加一, 不足 0 则直接从购物车移出
		for (int i = 0; i < cart.getOrderDetails().size(); i++) {
			OrderDetail item = cart.getOrderDetails().get(i);
			if (item.getBook().getId() == bookId) {
				if (item.getCount() > 1) {
					item.setCount(item.getCount() - 1);
				} else {
					cart.getOrderDetails().remove(item);
				}
			}
		}
	}
}
