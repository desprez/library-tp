package fr.training.spring.library.exposition;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.library.Type;

public class LibraryDTO {

	@JsonProperty
	Type type;

	@JsonProperty
	AddressDTO adressDTO;

	@JsonProperty
	DirectorDTO directorDTO;

	@JsonProperty
	List<BookDTO> bookDTOList;

	public LibraryDTO(final Type type, final AddressDTO adressDTO, final DirectorDTO directorDTO, final List<BookDTO> bookDTOList) {
		this.type = type;
		this.adressDTO = adressDTO;
		this.directorDTO = directorDTO;
		this.bookDTOList = bookDTOList;
	}

}
