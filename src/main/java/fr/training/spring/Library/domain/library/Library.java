package fr.training.spring.Library.domain.library;

import java.util.List;

import fr.training.spring.Library.domain.library.book.Book;
public class Library {

	private Long id;

	private Type type;

	private Address address;

	private Director director;

	private List<Book> books;

	public Library() {}

	public Library(final Long id, final Type type, final Address address, final Director director, final List<Book> books) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.director = director;
		this.books = books;
	}

	public void update(final Library libraryWithNewInformation) {
		type = libraryWithNewInformation.getType();
		address = libraryWithNewInformation.getAddress();
		director = libraryWithNewInformation.getDirector();
	}

	public Long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public Address getAddress() {
		return address;
	}

	public Director getDirector() {
		return director;
	}

	public List<Book> getBooks() {
		return books;
	}
}