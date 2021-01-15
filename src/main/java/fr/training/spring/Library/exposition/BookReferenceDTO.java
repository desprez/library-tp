package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.library.book.LiteraryGenre;

public class BookReferenceDTO {

	@JsonProperty
	final String isbn;

	@JsonProperty
	final LiteraryGenre literaryGenre;

	public BookReferenceDTO(final String isbn, final LiteraryGenre literaryGenre) {
		this.isbn = isbn;
		this.literaryGenre = literaryGenre;
	}

	public String getIsbn() {
		return isbn;
	}

	public LiteraryGenre getLiteraryGenre() {
		return literaryGenre;
	}

}
