package fr.training.spring.library.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.library.book.LiteraryGenre;

public class BookDTO {

	@JsonProperty
	String isbn;

	@JsonProperty
	String title;

	@JsonProperty
	String author;

	@JsonProperty
	int numberOfPage;

	@JsonProperty
	LiteraryGenre literaryGenre;

	public BookDTO(final String isbn, final String title, final String author, final int numberOfPage, final LiteraryGenre literaryGenre) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.numberOfPage = numberOfPage;
		this.literaryGenre = literaryGenre;
	}

}
