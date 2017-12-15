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
@Table(name = "Book")
public class Book {

	private int id;
	private String name;
	private BookType type;
	private String author;
	private String publisher;
	private String publishData;
	private double price;
	private String imageUrl;

	private Set<OrderDetail> details = new HashSet<>();

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

	@ManyToOne(targetEntity = BookType.class)
	@JoinTable(name = "book2type",
			joinColumns = @JoinColumn(name = "book_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "type_id"
					, referencedColumnName = "id")
	)
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishData() {
		return publishData;
	}
	public void setPublishData(String publishData) {
		this.publishData = publishData;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
	@JoinTable(name = "detail2book",
			joinColumns = @JoinColumn(name = "book_id"
					, referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "detail_id"
					, referencedColumnName = "id")
	)
	public Set<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}
}
