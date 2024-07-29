package ro.wantsome.databases.web;

import org.springframework.stereotype.Controller;
import ro.wantsome.databases.domain.Author;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.service.BookService;

import javax.annotation.PostConstruct;

@Controller
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostConstruct
	void init(){
		Book harryPotter = new Book();
		harryPotter.setTitle("Harry Potter and the Philosopher's Stone");
		harryPotter.setPrice(50.0);
		harryPotter.setAuthor(new Author("J.K. Rowling", 60.0));

		System.out.println("Saving book: " + harryPotter);

		bookService.saveBook(harryPotter);

		Book foundBook = bookService.findById(harryPotter.getId());

		System.out.println("Found book: " + foundBook);

		Book hobbit = new Book();
		hobbit.setTitle("The Hobbit");
		hobbit.setPrice(40.0);
		hobbit.setAuthor(new Author("J.R.R. Tolkien", 70.0));

		System.out.println("Saving book: " + hobbit);

		bookService.saveBook(hobbit);

		Book foundHobbit = bookService.findById(hobbit.getId());

		System.out.println("Found book: " + foundHobbit);

		foundHobbit.setPrice(45.0);

		bookService.saveBook(foundHobbit);

		for (Book b : bookService.findAll()) {
			System.out.println("Found book by findAll(): " + b);
		}
	}
}
