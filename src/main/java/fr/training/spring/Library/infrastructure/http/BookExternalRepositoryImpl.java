package fr.training.spring.library.infrastructure.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.OpenLibraryTechnicalException;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.BookRepository;
import fr.training.spring.library.infrastructure.http.dto.AuthorInfo;
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

			// Search Author with the first BookInfo author key
			final String authorName = searchAuthor(bookInfo.getAuthors().get(0).getKey());

			return new Book(null, isbn, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
				throw new NotFoundException("Book # " + isbn + "Not found", ErrorCodes.BOOK_NOT_FOUND);
			}
			throw new OpenLibraryTechnicalException(e);
		}
	}

	private String searchAuthor(final String authorKey) {
		final String authorName = "unknown";
		if (!authorKey.isEmpty()) {
			try {
				final ResponseEntity<AuthorInfo> response = restTemplate.getForEntity(authorKey + ".json",
						AuthorInfo.class);

				final AuthorInfo authorInfo = response.getBody();
				logger.debug(authorInfo.toString());

				return authorInfo.getName();
			} catch (final RestClientException e) {
				logger.error("Error on author call for " + authorKey);
			}
		}
		return authorName;
	}

}
