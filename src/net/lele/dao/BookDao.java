package net.lele.dao;

import net.lele.entity.Book;
import net.lele.entity.BookType;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao extends BaseDao<Book, Integer> {

	public List<Book> getAllBooks() {
		try{
			return super.findByProperty("from Book", null);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Book getBookById(int bookId) {

		try{
			return super.findOne("from Book book where book.id=?", new Object[] {bookId});
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Book> getBooksLikeName(String searchText) {
		try{
			return super.findByProperty("from Book book where book.name like ?", new Object[] {"%" + searchText + "%"});
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
