package fr.training.spring.library.domain.exception;

public class LibraryNotFoundException extends RuntimeException {

	private static final String ERROR_CODE = ErrorCodes.LIBRARY_NOT_FOUND;

	public LibraryNotFoundException(final String message) {
		super(message);
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}
}