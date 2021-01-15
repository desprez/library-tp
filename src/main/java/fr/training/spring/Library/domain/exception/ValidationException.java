package fr.training.spring.library.domain.exception;

public class ValidationException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ValidationException(final String message, final String errorCode) {
		super(message, errorCode);
	}

}