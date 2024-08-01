package ro.wantsome.databases.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book_table")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@Column(name = "title", nullable = false, length = 150)
	private String title;

	@Column(name = "price", nullable = false)
	private Double price;

	@Embedded
	private Author author;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Chapter> chapterList;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString()
	{
		return "Book{" + "id=" + id + ", version=" + version + ", title='" + title + '\'' + ", price="
					 + price + ", author=" + author + '}';
	}

	public List<Chapter> getChapterList()
	{
		return chapterList;
	}
	public void setChapterList(List<Chapter> chapterList)
	{
		this.chapterList = chapterList;
	}
}
