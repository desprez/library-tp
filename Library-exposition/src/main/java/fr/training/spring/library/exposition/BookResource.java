package fr.training.spring.library.exposition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.training.spring.library.application.BookService;
import fr.training.spring.library.domain.library.book.Book;

@RestController
public class BookResource {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	@ResponseStatus(HttpStatus.OK)
	public BookDTO searchBookByISBN(@RequestParam("isbn") final String isbn) {
		final Book book = bookService.searchBookByISBN(isbn);
		return new BookDTO(isbn, book.getTitle(), book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre());
	}
}
