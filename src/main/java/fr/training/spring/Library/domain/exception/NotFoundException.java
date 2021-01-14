package fr.training.spring.library.domain.exception;

public class NotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(final String message, final String errorCode) {
		super(message, errorCode);
	}

}