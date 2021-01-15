package fr.training.spring.library.exposition;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.LiteraryGenre;
import fr.training.spring.library.infrastructure.LibraryDAO;
import fr.training.spring.library.infrastructure.LibraryJPA;

// I was forced to move the database setup to another class due to the transactional state of the test class :
// Hibernate was lost when trying to save the same object multiple times in a non-transactional context
// And putting the @Transactional at method level was too specific whereas putting it on top of test class was too large
// Putting it in another @Transactional annotated class works just fine !
@Transactional
@Component
public class DatabaseTestHelper {

	@Autowired
	LibraryDAO libraryDAO;

	public static final Book DONQUIXOTE = new Book(null, "843762214X", "Don Quixote", "Miguel de Cervantes", 200,
			LiteraryGenre.TRAGEDY);
	public static final Book ATALEOFTWOCITIES = new Book(null, "1657599086", "A Tale of Two Cities", "Charles Dickens",
			300, LiteraryGenre.FANTASTIC);
	public static final Book LORDOFTHERINGS = new Book(null, "9780261102385", "The Lord of the Rings", "J.R.R. Tolkien",
			500, LiteraryGenre.EPIC);
	public static final Book HARRYPOTTER1 = new Book(null, "9781408855652", "Harry Potter and the Sorcerer’s Stone",
			"J.K. Rowling", 200, LiteraryGenre.FANTASTIC);
	public static final Book DAVINCICODE = new Book(null, "052556585X", "The Da Vinci Code", "Dan Brown", 300,
			LiteraryGenre.FANTASTIC);
	public static final Book ILIUM = new Book(null, "9780380817924", "Ilium", "Dan Simmons", 600,
			LiteraryGenre.FANTASTIC);
	public static final Book SPRING_IN_ACTION = new Book(null, "1617294942", "Spring in Action", "Craig Walls", 310,
			LiteraryGenre.TECHNICAL);

	public static final Library NATIONAL_LIBRARY_MONTREUIL = new Library(null, Type.NATIONAL,
			new Address(1, "Rue de Montreuil1", 93101, "Montreuil"), new Director("Romain", "NOEL"), Arrays.asList());
	public static final Library SCHOOL_LIBRARY_PARIS = new Library(null, Type.NATIONAL,
			new Address(2, "Rue de Montreuil2", 93102, "Montreuil2"), new Director("Garfield", "LECHAT1"),
			Arrays.asList(DONQUIXOTE, ATALEOFTWOCITIES));
	public static final Library NATIONAL_LIBRARY_MONTREUIL2 = new Library(null, Type.SCHOOL,
			new Address(3, "Rue de Paris1", 75001, "Paris1"), new Director("Romain", "NOEL"), Arrays.asList());
	public static final Library SCHOOL_LIBRARY_PARIS2 = new Library(null, Type.SCHOOL,
			new Address(4, "Rue de Paris2", 75002, "Paris2"), new Director("Garfield", "LECHAT2"),
			Arrays.asList(LORDOFTHERINGS, HARRYPOTTER1));
	public static final Library PUBLIC_LIBRARY_VINCENNES = new Library(null, Type.PUBLIC,
			new Address(5, "Rue de Vincennes", 94200, "Vincennes"), new Director("Garfield", "LECHAT3"),
			Arrays.asList(DAVINCICODE, ILIUM, LORDOFTHERINGS));
	public static final Library DUMMY_LIBRARY = new Library(null, Type.SCHOOL, new Address(0, "DUMMY_STREET", 0, "DUMMY_CITY"),
			new Director("DUMMY_NAME", "DUMMY_SURNAME"), Arrays.asList(SPRING_IN_ACTION));

	public static final LibraryJPA NATIONAL_LIBRARY_MONTREUIL_JPA = new LibraryJPA(NATIONAL_LIBRARY_MONTREUIL);
	public static final LibraryJPA SCHOOL_LIBRARY_PARIS_JPA = new LibraryJPA(SCHOOL_LIBRARY_PARIS);
	public static final LibraryJPA NATIONAL_LIBRARY_MONTREUIL2_JPA = new LibraryJPA(NATIONAL_LIBRARY_MONTREUIL2);
	public static final LibraryJPA SCHOOL_LIBRARY_PARIS2_JPA = new LibraryJPA(SCHOOL_LIBRARY_PARIS2);
	public static final LibraryJPA PUBLIC_LIBRARY_VINCENNES_JPA = new LibraryJPA(PUBLIC_LIBRARY_VINCENNES);
	public static final LibraryJPA DUMMY_LIBRARY_JPA = new LibraryJPA(DUMMY_LIBRARY);

	public void setup() {
		libraryDAO.deleteAll();
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL_JPA);
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL2_JPA);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS_JPA);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS2_JPA);
		libraryDAO.save(PUBLIC_LIBRARY_VINCENNES_JPA);
		libraryDAO.flush();
	}

	public void tearDown() {
		libraryDAO.deleteAll();
		libraryDAO.flush();
	}

	public LibraryJPA createDummyLibrary() {
		return libraryDAO.save(DUMMY_LIBRARY_JPA);
	}
}