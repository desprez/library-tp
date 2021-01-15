package fr.training.spring.library.infrastructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.book.Book;

@Entity
@Table(name = "LIBRARY")
public class LibraryJPA {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;

	@Column(name = "ADDRESS_NUMBER")
	private int addressNumber;

	@Column(name = "ADDRESS_STREET")
	private String addressStreet;

	@Column(name = "ADDRESS_POSTALCODE")
	private int addressPostalCode;

	@Column(name = "ADDRESS_CITY")
	private String addressCity;

	@Column(name = "DIRECTOR_SURNAME")
	private String directorSurname;

	@Column(name = "DIRECTOR_NAME")
	private String directorName;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "LIBRARY_ID", referencedColumnName = "ID")
	private List<BookJPA> books;

	private LibraryJPA() {

	}

	/**
	 * Copy constructor
	 */
	public LibraryJPA(final Library library) {
		id = library.getId();
		type = library.getType();
		addressNumber = library.getAddress().getNumber();
		addressStreet = library.getAddress().getStreet();
		addressPostalCode = library.getAddress().getPostalCode();
		addressCity = library.getAddress().getCity();
		directorName = library.getDirector().getName();
		directorSurname = library.getDirector().getSurname();
		books = new ArrayList<>();
		for (final Book book : library.getBooks()) {
			books.add(new BookJPA(book));
		}
	}

	public Library toLibrary() {
		final Address address = new Address(addressNumber, addressStreet, addressPostalCode, addressCity);
		final Director director = new Director(directorSurname, directorName);
		final List<Book> bookList = new ArrayList<>();
		for (final BookJPA bookJPA : books) {
			bookList.add(new Book(bookJPA.getId(), bookJPA.getIsbn(), bookJPA.getTitle(), bookJPA.getAuthor(),
					bookJPA.getNumberOfPage(), bookJPA.getLiteraryGenre()));
		}
		return new Library(id, type, address, director, bookList);
	}

	public Long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public int getAddressNumber() {
		return addressNumber;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public int getAddressPostalCode() {
		return addressPostalCode;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public String getDirectorSurname() {
		return directorSurname;
	}

	public String getDirectorName() {
		return directorName;
	}

	public List<BookJPA> getBooks() {
		return books;
	}

}
