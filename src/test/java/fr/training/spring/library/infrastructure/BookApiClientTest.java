package fr.training.spring.library.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.training.spring.library.infrastructure.http.author.AuthorInfo;
import fr.training.spring.library.infrastructure.http.book.BookInfo;

public class BookApiClientTest {

	@Test
	public void getBookInfo_form_openLibrary() throws URISyntaxException {
		final RestTemplate restTemplate = new RestTemplate();

		final String isbn = "0321125215";
		final String baseUrl = "https://openlibrary.org";

		final ResponseEntity<BookInfo> response = restTemplate
				.getForEntity(new URI(baseUrl + "/isbn/" + isbn + ".json"), BookInfo.class);

		System.out.println(response.getBody());

		// Verify request succeed
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// assertThat(response);
	}

	@Test
	public void getAuthorInfo_form_openLibrary() throws URISyntaxException {
		final RestTemplate restTemplate = new RestTemplate();

		final String author = "OL890061A";
		final String baseUrl = "https://openlibrary.org";

		final ResponseEntity<AuthorInfo> response = restTemplate
				.getForEntity(new URI(baseUrl + "/authors/" + author + ".json"), AuthorInfo.class);

		System.out.println(response.getBody());

		// Verify request succeed
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// assertThat(response);
	}
}
