package fr.training.spring.library.domain.library;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import fr.training.spring.library.domain.library.book.Book;

@Entity(name = "LIBRARY")
public class Library {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;

	@Embedded
	private Address address;

	@Embedded
	private Director director;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "LIBRARY_ID", referencedColumnName = "ID")
	private List<Book> books;

	public Library() {
	}

	public Library(final Type type, final Address address, final Director director, final List<Book> books) {
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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Library [id=").append(id).append(", type=").append(type).append(", address=").append(address)
		.append(", director=").append(director).append(", books=").append(books).append("]");
		return builder.toString();
	}



}