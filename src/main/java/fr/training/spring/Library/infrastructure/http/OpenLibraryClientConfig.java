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

	private static final String BASE_URL = "https://openlibrary.org";

	@Bean
	public RestTemplate getRestClient(final RestTemplateBuilder restTemplateBuilder) {

		final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		return restTemplateBuilder.interceptors(interceptors) //
				.rootUri(BASE_URL) //
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}

}