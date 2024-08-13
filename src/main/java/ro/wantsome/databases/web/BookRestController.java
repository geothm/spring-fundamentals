package ro.wantsome.databases.web;

import org.springframework.web.bind.annotation.*;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {

	private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

	@GetMapping("")
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.findById(id);
	}

	@PostMapping("")
	public void updateByBookId(@RequestBody Book book) {
		bookService.saveBook(book);
	}

	@DeleteMapping("/{id}")
	public void deleteByBookId(@PathVariable Long id) {
		bookService.deleteById(id);
	}

	@PutMapping("/{id}")
	public void updateByBookId(@RequestBody Book book, @PathVariable Long id )  {
		bookService.updateBookById(id, book);
	}

}
