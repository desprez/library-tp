package fr.training.spring.library.exposition;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.book.LiteraryGenre;

//Here, encapsulation can be debated because the internal structure does not have to be hidden
//and the DTO is not supposed to have any kind of intelligence
public class LibraryDTO {
	@JsonProperty
	final Type type;

	@JsonProperty
	final AddressDTO addressDTO;

	@NotNull(message = ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR)
	@JsonProperty
	final DirectorDTO directorDTO;

	@JsonProperty
	final List<BookDTO> bookDTOList;

	public LibraryDTO(final Type type, final AddressDTO addressDTO, final DirectorDTO directorDTO,
			final List<BookDTO> bookDTOList) {
		this.type = type;
		this.addressDTO = addressDTO;
		this.directorDTO = directorDTO;
		this.bookDTOList = Collections.unmodifiableList(bookDTOList);
	}

	public static class DirectorDTO {
		@JsonProperty
		final String surname;
		@JsonProperty
		final String name;

		public DirectorDTO(final String surname, final String name) {
			this.surname = surname;
			this.name = name;
		}
	}

	public static class AddressDTO {
		@JsonProperty
		final int number;
		@JsonProperty
		final String street;
		@JsonProperty
		final int postalCode;
		@JsonProperty
		final String city;

		public AddressDTO(final int number, final String street, final int postalCode, final String city) {
			this.number = number;
			this.street = street;
			this.postalCode = postalCode;
			this.city = city;
		}
	}

	public static class BookDTO {
		@JsonProperty
		final String isbn;
		@JsonProperty
		final String title;
		@JsonProperty
		final String author;
		@JsonProperty
		final int numberOfPage;
		@JsonProperty
		final LiteraryGenre literaryGenre;

		public BookDTO(final String isbn, final String title, final String author, final int numberOfPage,
				final LiteraryGenre literaryGenre) {
			this.isbn = isbn;
			this.title = title;
			this.author = author;
			this.numberOfPage = numberOfPage;
			this.literaryGenre = literaryGenre;
		}
	}
}