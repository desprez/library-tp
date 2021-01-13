package fr.training.spring.library.domain.library;

public class Address {

	private int number;

	private String street;

	private int postalCode;

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