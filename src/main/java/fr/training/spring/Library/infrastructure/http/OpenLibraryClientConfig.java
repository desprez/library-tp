package fr.training.spring.library.infrastructure.http;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenLibraryClientConfig {

	// Best practice is to externalize this url in properties
	private static final String BASE_URL = "https://openlibrary.org";

	/**
	 * Configure a RestTemplate with errorHandler to call openlibrary.org
	 *
	 * @param restTemplateBuilder injected By Spring
	 *
	 * @return a RestTemplate
	 */
	@Bean
	public RestTemplate getRestClient(final RestTemplateBuilder restTemplateBuilder) {

		final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		return restTemplateBuilder.interceptors(interceptors) //
				.rootUri(BASE_URL) //
				.build();
	}

}