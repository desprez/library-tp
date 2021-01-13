package fr.training.spring.library.domain.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_CODE = ErrorCodes.LIBRARY_NOT_FOUND;

	public ValidationException(final String message) {
		super(message);
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}
}