package fr.training.spring.library.domain.common.exception;

public class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = -1L;

	public EntityNotFoundException(final String message) {
		super(message);
	}

	public EntityNotFoundException() {
		super();
	}

	@Override
	public String getCode() {
		return "func.err.01";
	}
}
