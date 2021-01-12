package fr.training.spring.Library;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name = "ADDRESS_NUMBER")
	private int number;

	@Column(name = "ADDRESS_STREET")
	private String street;

	@Column(name = "ADDRESS_POSTALCODE")
	private int postalCode;

	@Column(name = "ADDRESS_CITY")
	private String city;

	public Address() {}

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
}