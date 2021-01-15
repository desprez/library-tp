package fr.training.spring.library.domain.library;

import java.util.List;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.library.book.Book;

@DDD.Entity
public class Library {

	private Long id;

	private Type type;

	private Address address;

	private Director director;

	private List<Book> books;

	public Library() {
	}

	public Library(final Long id, final Type type, final Address address, final Director director,
			final List<Book> books) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.director = director;
		this.books = books;
		validate();
	}

	public void update(final Library libraryWithNewInformation) {
		type = libraryWithNewInformation.getType();
		address = libraryWithNewInformation.getAddress();
		director = libraryWithNewInformation.getDirector();
		validate();
	}

	private void validate() {
		director.validate();
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

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}

		if (!this.getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}

		final Library that = this.getClass().cast(obj);

		return that.id.equals(id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return String.format("%s{id:%s)", this.getClass().getSimpleName(), id);
	}

	public void addBook(final Book book) {
		// TODO Auto-generated method stub

	}
}