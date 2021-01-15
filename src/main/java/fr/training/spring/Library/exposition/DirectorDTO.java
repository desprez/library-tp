package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectorDTO {

	@JsonProperty
	String name;

	@JsonProperty
	String surname;

	public DirectorDTO(final String name, final String surname) {
		this.name = name;
		this.surname = surname;
	}

}
