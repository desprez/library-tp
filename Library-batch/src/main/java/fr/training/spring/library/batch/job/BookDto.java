package fr.training.spring.library.batch.job;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.training.spring.library.domain.library.book.LiteraryGenre;

public class BookDto {

	private long id;

	@JsonProperty
	private String isbn;

	@JsonProperty
	private String title;

	@JsonProperty
	private String author;

	@JsonProperty
	private int numberOfPage;

	@JsonProperty
	private LiteraryGenre literaryGenre;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(final int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	public LiteraryGenre getLiteraryGenre() {
		return literaryGenre;
	}

	public void setLiteraryGenre(final LiteraryGenre literaryGenre) {
		this.literaryGenre = literaryGenre;
	}

}
