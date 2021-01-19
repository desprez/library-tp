package fr.training.spring.library.batch.job;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.library.Type;

public class LibraryDto {

	@JsonProperty
	private Long id;

	@JsonProperty
	private Type type;

	@JsonProperty
	private int addressNumber;

	@JsonProperty
	private String addressStreet;

	@JsonProperty
	private int addressPostalCode;

	@JsonProperty
	private String addressCity;

	@JsonProperty
	private String directorSurname;

	@JsonProperty
	private String directorName;

	@JsonProperty("books")
	private List<BookDto> books = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(final int addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(final String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public int getAddressPostalCode() {
		return addressPostalCode;
	}

	public void setAddressPostalCode(final int addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(final String addressCity) {
		this.addressCity = addressCity;
	}

	public String getDirectorSurname() {
		return directorSurname;
	}

	public void setDirectorSurname(final String directorSurname) {
		this.directorSurname = directorSurname;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(final String directorName) {
		this.directorName = directorName;
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(final List<BookDto> books) {
		this.books = books;
	}

}
