package net.lele.dao;

import net.lele.entity.BookType;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookTypeDao extends BaseDao<BookType, Integer> {

	public List<BookType> getAllBookTypes() {
		try{
			return super.findByProperty("from BookType", null);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BookType getBookTypeById(Integer bookTypeId) {
		try{
			return super.findOne("from BookType where id=?", new Object[] {bookTypeId});
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
