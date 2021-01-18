package fr.training.spring.library.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.BookRepository;

@DDD.ApplicationService
@Transactional
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book searchBookByISBN(final String isbn) {
		return bookRepository.searchBook(isbn, null);
	}


}
