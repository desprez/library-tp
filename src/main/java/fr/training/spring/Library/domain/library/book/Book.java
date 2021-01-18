package fr.training.spring.library.domain.library.book;

import java.util.Objects;

import fr.training.spring.library.domain.ddd.DDD;

@DDD.Entity
public class Book {

	private Long id;

	private String isbn;

	private String title;

	private String author;

	private int numberOfPage;

	private LiteraryGenre literaryGenre;

	private Book() {
	}

	public Book(final Long id, final String isbn, final String title, final String author, final int numberOfPage,
			final LiteraryGenre literaryGenre) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.numberOfPage = numberOfPage;
		this.literaryGenre = literaryGenre;
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public LiteraryGenre getLiteraryGenre() {
		return literaryGenre;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		final Book other = (Book) obj;
		return Objects.equals(getId(), other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}



	@Override
	public String toString() {
		return String.format("%s{id:%s)", this.getClass().getSimpleName(), id);
	}
}