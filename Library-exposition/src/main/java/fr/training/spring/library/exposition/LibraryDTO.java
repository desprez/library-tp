package fr.training.spring.library.exposition;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.library.Type;

//Here, encapsulation can be debated because the internal structure does not have to be hidden
//and the DTO is not supposed to have any kind of intelligence
public class LibraryDTO {
	@JsonProperty
	public Type type;

	@JsonProperty
	public AddressDTO addressDTO;

	@NotNull(message = ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR)
	@JsonProperty
	public DirectorDTO directorDTO;

	@JsonProperty
	public List<BookDTO> bookDTOList;

	public LibraryDTO(final Type type, final AddressDTO addressDTO, final DirectorDTO directorDTO,
			final List<BookDTO> bookDTOList) {
		this.type = type;
		this.addressDTO = addressDTO;
		this.directorDTO = directorDTO;
		this.bookDTOList = Collections.unmodifiableList(bookDTOList);
	}

	public LibraryDTO() {

	}




}