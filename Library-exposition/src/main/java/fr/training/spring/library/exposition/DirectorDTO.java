package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectorDTO {

	@JsonProperty
	final String surname;
	@JsonProperty
	final String name;

	public DirectorDTO(final String surname, final String name) {
		this.surname = surname;
		this.name = name;
	}
}
