package net.lele.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@Entity
@Table(name = "BookType")
public class BookType {

	private int id;
	private String name;

	private Set<Book> books = new HashSet<>();


	public BookType() {}
	public BookType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	//设置主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
	@JoinTable(name = "book2type",
			joinColumns = @JoinColumn(name = "type_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"
					, referencedColumnName = "id")
	)
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
