package fr.training.spring.library.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorCode;

	protected BusinessException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}