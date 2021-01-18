package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectorDTO {

	@JsonProperty
	public String surname;
	@JsonProperty
	public String name;

	public DirectorDTO(final String surname, final String name) {
		this.surname = surname;
		this.name = name;
	}

	public DirectorDTO() {

	}
}
