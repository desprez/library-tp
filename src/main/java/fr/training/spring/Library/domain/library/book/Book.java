package fr.training.spring.library.domain.library.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "NUMBER_OF_PAGE")
	private int numberOfPage;

	@Enumerated(EnumType.STRING)
	@Column(name = "LITERARY_GENRE")
	private LiteraryGenre literaryGenre;

	private Book() {
	}

	public Book(final String isbn, final String title, final String author, final int numberOfPage,
			final LiteraryGenre literaryGenre) {
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
}