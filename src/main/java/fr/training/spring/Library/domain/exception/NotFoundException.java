package fr.training.spring.library.domain.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorCode;

	public NotFoundException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}