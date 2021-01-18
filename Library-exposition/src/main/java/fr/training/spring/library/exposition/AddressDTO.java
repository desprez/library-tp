package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {

	@JsonProperty
	public int number;
	@JsonProperty
	public String street;
	@JsonProperty
	public int postalCode;
	@JsonProperty
	public String city;

	public AddressDTO(final int number, final String street, final int postalCode, final String city) {
		this.number = number;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public AddressDTO() {

	}
}