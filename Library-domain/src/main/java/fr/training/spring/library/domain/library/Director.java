package fr.training.spring.library.domain.library;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.ValidationException;

@DDD.ValueObject
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
		if (this == null || StringUtils.isEmpty(surname) || StringUtils.isEmpty(name)) {
			throw new ValidationException("Director is null", ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR);
		}
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Director director = (Director) o;
		return surname.equals(director.surname) && name.equals(director.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(surname, name);
	}
}