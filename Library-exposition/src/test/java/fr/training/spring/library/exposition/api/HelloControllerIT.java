package fr.training.spring.library.exposition.api;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Integration Test example (launch server during tests)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		base = new URL("http", "localhost", port, "/hello");
	}

	@Test
	public void getHelloSuccess() {
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(base.toString()).queryParam("name",
				"Martin");
		final ResponseEntity<String> response = template.getForEntity(builder.toUriString(), String.class);
		assertThat(response.getBody(), equalTo("Hello Martin !"));
	}

	@Test
	public void getHelloMissingParameterFails() {
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(base.toString());
		final ResponseEntity<String> response = template.getForEntity(builder.toUriString(), String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	}

}