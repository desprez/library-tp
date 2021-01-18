package fr.training.spring.library.domain.library;

import com.google.common.base.Objects;

import fr.training.spring.library.domain.ddd.DDD;

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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Address)) {
			return false;
		}
		final Address other = (Address) obj;
		return Objects.equal(getNumber(), other.getNumber()) && //
				Objects.equal(getStreet(), other.getStreet()) &&  //
				Objects.equal(getPostalCode(), other.getPostalCode()) && //
				Objects.equal(getCity(), other.getCity());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNumber(), getStreet(), getPostalCode(), getCity());
	}

}