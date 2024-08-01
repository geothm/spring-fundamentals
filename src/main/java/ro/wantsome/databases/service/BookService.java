package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.domain.BookJpaRepository;
import ro.wantsome.databases.domain.BookRepository;

import java.util.List;

@Service
public class BookService {

	private final BookRepository bookRepository;

	private final BookJpaRepository bookJpaRepository;

	public BookService(BookRepository bookRepository, BookJpaRepository bookJpaRepository) {
		this.bookRepository = bookRepository;
		this.bookJpaRepository = bookJpaRepository;
	}

	@Transactional
	public void saveBook(Book book) {
		bookJpaRepository.save(book);
	}

	public Book findById(Long id) {
		return bookJpaRepository.findById(id).orElse(null);
	}

	public List<Book> findAll() {
		return bookJpaRepository.findAll();
	}

	public List<Book> findAllByTitle(String title) {
		return bookJpaRepository.findAllByTitle(title);
	}
}
