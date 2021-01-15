package fr.training.spring.library.infrastructure.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.BookRepository;
import fr.training.spring.library.infrastructure.http.dto.BookInfo;

@DDD.RepositoryImpl
@Component
public class BookRepositoryImpl implements BookRepository {

	private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Book searchBook(final String isbn) {

		final ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json", BookInfo.class);

		final BookInfo bookInfo = response.getBody();
		logger.info(bookInfo.toString());

		return new Book(null, isbn, bookInfo.getTitle(), "", bookInfo.getNumber_of_pages(), null);
	}

}
