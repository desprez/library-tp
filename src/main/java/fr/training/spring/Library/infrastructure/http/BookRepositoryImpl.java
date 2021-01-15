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
import fr.training.spring.library.infrastructure.http.author.AuthorInfo;
import fr.training.spring.library.infrastructure.http.book.BookInfo;

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
		final String authorKey = bookInfo.getAuthors().get(0).getKey();
		String authorName = "";
		if (!authorKey.isEmpty()) {
			try {
				authorName = getAuthorName(authorKey);
			} catch (final Exception e) {
				logger.error("Can get author for " + e);
			}
		}
		return new Book(null, isbn, bookInfo.getTitle(), authorName, bookInfo.getNumber_of_pages(), null);
	}

	public String getAuthorName(final String authorKey) {
		final ResponseEntity<AuthorInfo> response = restTemplate.getForEntity("/authors/" + authorKey + ".json",
				AuthorInfo.class);

		final AuthorInfo authorInfo = response.getBody();
		logger.info(authorInfo.toString());

		return authorInfo.getName();
	}

}
