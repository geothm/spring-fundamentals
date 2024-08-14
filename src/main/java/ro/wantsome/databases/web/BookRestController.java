package ro.wantsome.databases.web;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.wantsome.databases.domain.Book;
import ro.wantsome.databases.service.BookService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookRestController {

	private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

	@GetMapping("")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.findAll();
		if(books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = bookService.findById(id);

		WebMvcLinkBuilder link =  linkTo(BookRestController.class).slash((book.getId()));
		book.add(link.withRel("self"));

		Link linkToSelf = linkTo(methodOn(BookRestController.class).getAllBooks()).withRel("all books of the api");
		book.add(linkToSelf);

		return new ResponseEntity<>(book, HttpStatus.OK);
    }

	@PostMapping("")
	public ResponseEntity<String> updateByBookId(@RequestBody Book book) {
		bookService.saveBook(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteByBookId(@PathVariable Long id) {
		bookService.findById(id);
		bookService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> updateByBookId(@RequestBody Book book, @PathVariable Long id )  {
		bookService.updateBookById(id, book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
