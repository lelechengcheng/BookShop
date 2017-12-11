package net.onest.dao;

import net.onest.bean.Book;
import net.onest.bean.BookType;
import net.onest.bean.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
public class BookDao {

	private static Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setId(resultSet.getInt("book_id"));
		book.setName(resultSet.getString("book_name"));
		book.setPrice(resultSet.getDouble("book_price"));
		book.setAuthor(resultSet.getString("book_author"));
		book.setPublisher(resultSet.getString("book_publisher"));
		book.setPublishData(resultSet.getString("book_publish_data"));
		book.setImageUrl(resultSet.getString("book_img"));

		BookType type = BookDao.getBookTypeById(resultSet.getInt("type_id"));
		book.setType(type);

		return book;
	}

	public static List<BookType> getAllBookType() {
		List<BookType> types = new ArrayList<>();

		String sql = "select * from BookType";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				BookType type = new BookType();
				type.setId(resultSet.getInt("type_id"));
				type.setName(resultSet.getString("type_name"));
				types.add(type);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}

	public static BookType getBookTypeById(int bookTypeId) {

		BookType type = null;

		String sql = "select * from BookType where type_id=?";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setInt(1, bookTypeId);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				type = new BookType();
				type.setId(resultSet.getInt("type_id"));
				type.setName(resultSet.getString("type_name"));
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}

	public static List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();

		String sql = "select * from Book";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			ResultSet resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				Book book = BookDao.getBookFromResultSet(resultSet);
				books.add(book);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	public static Book getBookById(int bookId) {

		Book book = null;

		String sql = "select * from Book where book_id=?";
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setInt(1, bookId);
			ResultSet resultSet = preStatement.executeQuery();

			if (resultSet.next()) {
				book = BookDao.getBookFromResultSet(resultSet);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	public static int createBook(Book book) {
		String sqlCreateBook = "insert into Book (book_name, book_author, book_publisher, book_publish_data, book_price, type_id) values(?, ?, ?, ?, ?, ?)";
		String sqlUpdateBook = "update Book set book_img=? where book_id=?";

		int bookId = -1;
		try {
			PreparedStatement psBook = MySqlConnection.getConnection().prepareStatement(sqlCreateBook);
			psBook.setString(1, book.getName());
			psBook.setString(2, book.getAuthor());
			psBook.setString(3, book.getPublisher());
			psBook.setString(4, book.getPublishData());
			psBook.setDouble(5, book.getPrice());
			psBook.setInt(6, book.getType().getId());

			psBook.executeUpdate();
			ResultSet rsBook = psBook.getGeneratedKeys();
			if (rsBook.next()) {
				// 获取自增的 bookID
				bookId = rsBook.getInt(1);

				if (book.getImageUrl() != null && !book.getImageUrl().equals("")) {
					// 如果 book.imageUrl 有值, 更新到数据库
					PreparedStatement psUpdateBook = MySqlConnection.getConnection().prepareStatement(sqlUpdateBook);
					String bookImageURL = book.getImageUrl().replace("?", bookId + "");
					psUpdateBook.setString(1, bookImageURL);
					psUpdateBook.setInt(2, bookId);
					psUpdateBook.executeUpdate();
					psUpdateBook.close();
				}
			}

			rsBook.close();
			psBook.close();

			return bookId;

		} catch (SQLException e) {
			e.printStackTrace();
			return bookId;
		}
	}

	public static boolean deleteBookById(int id) {
		String sqlDeleteBook = "delete from Book where book_id=?";

		OrderDao.deleteOrderDetailsByBookId(id);

		try {
			PreparedStatement psBook = MySqlConnection.getConnection().prepareStatement(sqlDeleteBook);
			psBook.setInt(1, id);
			psBook.executeUpdate();
			psBook.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateBook(Book book) {

		String sqlCreateBook = "update Book set book_name=?, book_author=?, book_publisher=?, book_publish_data=?, book_price=?, type_id=?, book_img=? where book_id=?";

		String imgUrl = book.getImageUrl();
		imgUrl = imgUrl.replace("?", book.getId() + "");

		try {
			PreparedStatement psBook = MySqlConnection.getConnection().prepareStatement(sqlCreateBook);

			psBook.setString(1, book.getName());
			psBook.setString(2, book.getAuthor());
			psBook.setString(3, book.getPublisher());
			psBook.setString(4, book.getPublishData());
			psBook.setDouble(5, book.getPrice());
			psBook.setInt(6, book.getType().getId());
			psBook.setString(7, imgUrl);
			psBook.setInt(8, book.getId());

			psBook.executeUpdate();
			psBook.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static List<Book> getBooksLikeName(String searchText) {
		List<Book> books = new ArrayList<>();
		searchText = "%" + searchText.trim().replace(" ", "%") + "%";

		String sql = "select * from Book where book_name like ?";
		System.out.println(searchText);
		try {
			PreparedStatement preStatement = MySqlConnection.getConnection().prepareStatement(sql);
			preStatement.setString(1, searchText);
			ResultSet resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				Book book = BookDao.getBookFromResultSet(resultSet);
				books.add(book);
			}

			resultSet.close();
			preStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
}
