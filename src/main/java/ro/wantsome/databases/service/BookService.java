package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.domain.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public Book findById(Long id) {
		return bookRepository.findById(id);
	}
}
