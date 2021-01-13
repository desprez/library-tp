package fr.training.spring.library.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.LiteraryGenre;

@Entity(name = "BOOK")
public class BookJPA {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "NUMBER_OF_PAGE")
	private int numberOfPage;

	@Enumerated(EnumType.STRING)
	@Column(name = "LITERARY_GENRE")
	private LiteraryGenre literaryGenre;

	private BookJPA() {
	}

	public BookJPA(final Book book) {
		id = book.getId();
		title = book.getTitle();
		author = book.getAuthor();
		numberOfPage = book.getNumberOfPage();
		literaryGenre = book.getLiteraryGenre();
	}

	public Long getId() {
		return id;
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