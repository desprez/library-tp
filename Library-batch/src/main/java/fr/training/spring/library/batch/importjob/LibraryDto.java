package fr.training.spring.library.batch.importjob;

import fr.training.spring.library.domain.library.Type;

public class LibraryDto {

	private Type type;

	private int addressNumber;

	private String addressStreet;

	private int addressPostalCode;

	private String addressCity;

	private String directorSurname;

	private String directorName;

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

}
