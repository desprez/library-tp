package fr.training.spring.library.domain.exception;

public class OpenLibraryTechnicalException extends TechnicalException {

	private static final long serialVersionUID = 1L;

	public OpenLibraryTechnicalException(final Exception e) {
		super(e);
	}
}
