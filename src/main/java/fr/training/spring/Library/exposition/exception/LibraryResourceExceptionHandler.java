package fr.training.spring.library.exposition.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.ValidationException;

@ControllerAdvice(basePackages = "fr.training.spring.library")
public class LibraryResourceExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryResourceExceptionHandler.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String libraryNotFound(final NotFoundException exception) {
		final String codeErreur = exception.getErrorCode();
		LOGGER.info("Error {} : {}", codeErreur, exception.getMessage());
		return codeErreur;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public String validationFailed(final ValidationException exception) {
		final String codeErreur = exception.getErrorCode();
		LOGGER.info("Error {} : {}", codeErreur, exception.getMessage());
		return codeErreur;
	}

}