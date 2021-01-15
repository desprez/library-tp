package fr.training.spring.library.infrastructure.http;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.OpenLibraryTechnicalException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {

		return httpResponse.getStatusCode().series() == Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == Series.SERVER_ERROR;
	}

	@Override
	public void handleError(final ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			// handle SERVER_ERROR
			throw new OpenLibraryTechnicalException();
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			// handle CLIENT_ERROR
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new NotFoundException("Not found", ErrorCodes.BOOK_NOT_FOUND);
			}
		}
	}
}