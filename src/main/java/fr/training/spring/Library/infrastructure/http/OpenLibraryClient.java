package fr.training.spring.library.infrastructure.http;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.domain.exception.TechnicalException;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.infrastructure.http.author.AuthorInfo;
import fr.training.spring.library.infrastructure.http.book.BookInfo;

@Component
public class OpenLibraryClient {

	private static final Logger logger = LoggerFactory.getLogger(OpenLibraryClient.class);

	private static final String BASE_URL = "https://openlibrary.org";

	@Autowired
	private RestTemplate restTemplate;

	public Book getBookInfo(final String isbn) {
		ResponseEntity<BookInfo> response;
		try {
			response = restTemplate.getForEntity(new URI(BASE_URL + "/isbn/" + isbn + ".json"), BookInfo.class);
		} catch (RestClientException | URISyntaxException e) {
			throw new TechnicalException();
		}
		final BookInfo bookInfo = response.getBody();
		logger.info(bookInfo.toString());

		final String authorKey = bookInfo.getAuthors().get(0).getKey();

		final String authorName = getAuthorName(authorKey);

		return new Book(null, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);
	}

	public String getAuthorName(final String authorKey) {
		ResponseEntity<AuthorInfo> response;
		try {
			response = restTemplate.getForEntity(new URI(BASE_URL + "/authors/" + authorKey + ".json"),
					AuthorInfo.class);
		} catch (RestClientException | URISyntaxException e) {
			throw new TechnicalException();
		}
		final AuthorInfo authorInfo = response.getBody();
		logger.info(authorInfo.toString());

		return authorInfo.getName();
	}
}
