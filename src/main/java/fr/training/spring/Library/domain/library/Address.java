package fr.training.spring.Library.domain.library;

import java.util.Objects;

import fr.training.spring.Library.domain.ddd.DDD;

@DDD.ValueObject
public class Address {

	private int number;

	private String street;

	private int postalCode;

	private String city;

	public Address() {
	}

	public Address(final int number, final String street, final int postalCode, final String city) {
		this.number = number;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public int getNumber() {
		return number;
	}

	public String getStreet() {
		return street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Address address = (Address) o;
		return number == address.number && postalCode == address.postalCode && street.equals(address.street)
				&& city.equals(address.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, street, postalCode, city);
	}
}