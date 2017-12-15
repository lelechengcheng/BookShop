package net.lele.service;

import net.lele.dao.BookTypeDao;
import net.lele.entity.Book;
import net.lele.dao.BookDao;
import net.lele.entity.BookType;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class BookService {

	@Resource
	private BookDao bookDao;
	@Resource
	private BookTypeDao bookTypeDao;

	public List<Book> getAllBooks() {
		return this.bookDao.getAllBooks();
	}

	public Book getBook(int bookId) {
		return this.bookDao.getBookById(bookId);
	}

	public List<BookType> getAllBookTypes() {
		return this.bookTypeDao.getAllBookTypes();
	}

	public List<Book> getBooksLikeName(String searchText) {
		return this.bookDao.getBooksLikeName(searchText);
	}

	public void deleteBook(Integer id) {
		this.bookDao.delete(this.bookDao.getBookById(id));
	}

	public BookType getBookType(Integer bookTypeId) {
		return this.bookTypeDao.getBookTypeById(bookTypeId);
	}

	public int createBook(Book book) {

		if (this.bookDao.save(book)) {
			int id = book.getId();
			if (book.getImageUrl() != null) {
				book.setImageUrl(book.getImageUrl().replace("?", id + ""));
			}
			this.bookDao.update(book);
			return id;
		} else {
			return -1;
		}
	}

	public boolean updateBook(Book book) {

		Book b = this.bookDao.getBookById(book.getId());
		b.setId(book.getId());
		if (book.getName() != null && !book.getName().equals("")) b.setName(book.getName());
		if (book.getAuthor() != null && !book.getAuthor().equals("")) b.setAuthor(book.getAuthor());
		if (book.getPrice() > 0) b.setAuthor(book.getAuthor());
		if (book.getPublisher() != null && !book.getPublisher().equals("")) b.setPublisher(book.getPublisher());
		if (book.getPublishData() != null && !book.getPublishData().equals("")) b.setPublishData(book.getPublishData());
		if (book.getType() != null) b.setType(book.getType());

		if (book.getImageUrl() != null && !book.getImageUrl().equals("")) {
			b.setImageUrl(book.getImageUrl().replace("?", book.getId() + ""));
		}

		return this.bookDao.update(b);
	}

	public List<Book> getHotBooks() {
		List<Book> result = this.bookDao.getAllBooks();
		// 模拟热门图书筛选
		for (int i = 0; i < result.size() / 2; ++i) {
			result.remove(0);
		}
		return result;
	}

	public List<Book> getNewBooks() {
		List<Book> result = this.bookDao.getAllBooks();
		// 模拟最新图书筛选
		for (int i = result.size() / 2; i < result.size(); ++i) {
			result.remove(0);
		}
		return result;
	}
}
