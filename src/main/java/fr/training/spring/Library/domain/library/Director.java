package fr.training.spring.library.domain.library;

import org.springframework.util.StringUtils;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.ValidationException;

public class Director {

	private String surname;

	private String name;

	public Director() {
	}

	public Director(final String surname, final String name) {
		this.surname = surname;
		this.name = name;
	}

	public void validate() {
		if (this == null || StringUtils.isEmpty(surname) || StringUtils.isEmpty(name == null)) {
			throw new ValidationException(ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR);
		}
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}
}