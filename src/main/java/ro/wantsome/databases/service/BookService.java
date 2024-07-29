package ro.wantsome.databases.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.domain.BookRepository;

import java.util.List;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public Book findById(Long id) {
		return bookRepository.findById(id);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}
}
