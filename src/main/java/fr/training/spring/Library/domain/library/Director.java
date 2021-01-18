package fr.training.spring.library.domain.library;

import org.springframework.util.StringUtils;

import com.google.common.base.Objects;

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
		if (this == null || StringUtils.isEmpty(surname) || StringUtils.isEmpty(name == null)) {
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Director)) {
			return false;
		}
		final Director other = (Director) obj;
		return Objects.equal(getName(), other.getName()) && //
				Objects.equal(getSurname(), other.getSurname());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getName(), getSurname());
	}

}