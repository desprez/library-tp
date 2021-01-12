package fr.training.spring.Library.domain.library.book;

public class Book {

	private Long id;

	private String title;

	private String author;

	private int numberOfPage;

	private LiteraryGenre literaryGenre;

	private Book() {}

	public Book(final Long id, final String title, final String author, final int numberOfPage, final LiteraryGenre literaryGenre) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.numberOfPage = numberOfPage;
		this.literaryGenre = literaryGenre;
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