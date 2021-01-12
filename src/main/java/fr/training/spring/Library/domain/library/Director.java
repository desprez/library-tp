package fr.training.spring.Library.domain.library;

public class Director {

	private String surname;

	private String name;

	public Director() {}

	public Director(final String surname, final String name) {
		this.surname = surname;
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}
}