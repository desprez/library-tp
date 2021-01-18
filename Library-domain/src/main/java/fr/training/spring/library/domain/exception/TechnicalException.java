package fr.training.spring.library.domain.exception;

public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechnicalException(final Exception e) {
		super(e);
	}
}
