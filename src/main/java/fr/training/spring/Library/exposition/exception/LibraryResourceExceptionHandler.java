package fr.training.spring.library.exposition.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.training.spring.library.domain.exception.LibraryNotFoundException;

@ControllerAdvice(basePackages = "fr.training.spring.library")
public class LibraryResourceExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryResourceExceptionHandler.class);

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(LibraryNotFoundException.class)
	public String libraryNotFound(final LibraryNotFoundException exception) {
		final String codeErreur = exception.getErrorCode();
		LOGGER.info("Error {} : {}", codeErreur, exception.getMessage());
		return codeErreur;
	}

}