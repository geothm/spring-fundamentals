package ro.wantsome.databases.domain;

import jakarta.persistence.*;
@Entity
@Table(name = "chapter_table")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@Column(name = "title", nullable = false, length = 150)
	private String title;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	public Chapter(String title, Book book)
	{
		this.title = title;
		this.book = book;
	}

	public Chapter()
	{
	}
	public Book getBook()
	{
		return book;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public Long getId()
	{
		return id;
	}
}
