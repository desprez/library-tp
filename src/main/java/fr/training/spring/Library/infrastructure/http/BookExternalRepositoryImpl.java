package fr.training.spring.library.infrastructure.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.OpenLibraryTechnicalException;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.BookRepository;
import fr.training.spring.library.infrastructure.http.dto.BookInfo;

@DDD.RepositoryImpl
@Component
public class BookExternalRepositoryImpl implements BookRepository {

	private static final Logger logger = LoggerFactory.getLogger(BookExternalRepositoryImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Book searchBook(final String isbn) {
		try {

			final ResponseEntity<BookInfo> response = restTemplate.getForEntity("/isbn/" + isbn + ".json",
					BookInfo.class);

			final BookInfo bookInfo = response.getBody();


			logger.debug(bookInfo.toString());

			final String authorName = "unknown";

			return new Book(null, isbn, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				throw new NotFoundException("Book isbn " + isbn + " not found in openlibrary.org", ErrorCodes.BOOK_NOT_FOUND);
			}
			throw new OpenLibraryTechnicalException(e);
		}
	}

}
