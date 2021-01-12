package fr.training.spring.Library;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.Library.domain.library.Address;
import fr.training.spring.Library.domain.library.Director;
import fr.training.spring.Library.domain.library.Library;
import fr.training.spring.Library.domain.library.Type;
import fr.training.spring.Library.domain.library.book.Book;
import fr.training.spring.Library.domain.library.book.LiteraryGenre;
import fr.training.spring.Library.infrastructure.LibraryDAO;

// I was forced to move the database setup to another class due to the transactional state of the test class :
// Hibernate was lost when trying to save the same object multiple times in a non-transactional context
// And putting the @Transactional at method level was too specific whereas putting it on top of test class was too large
// Putting it in another @Transactional annotated class works just fine !
@Transactional
@Component
public class DatabaseTestHelper {

	@Autowired
	LibraryDAO libraryDAO;

	public static final Book DONQUIXOTE = new Book("Don Quixote", "Miguel de Cervantes", 200, LiteraryGenre.TRAGEDY);
	public static final Book ATALEOFTWOCITIES = new Book("A Tale of Two Cities", "Charles Dickens", 300,
			LiteraryGenre.FANTASTIC);
	public static final Book LORDOFTHERINGS = new Book("The Lord of the Rings", "J.R.R. Tolkien", 500,
			LiteraryGenre.EPIC);
	public static final Book HARRYPOTTER1 = new Book("Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", 200,
			LiteraryGenre.FANTASTIC);
	public static final Book DAVINCICODE = new Book("The Da Vinci Code", "Dan Brown", 300, LiteraryGenre.FANTASTIC);
	public static final Book ILIUM = new Book("Ilium", "Dan Simmons", 600, LiteraryGenre.FANTASTIC);

	public static final Library NATIONAL_LIBRARY_MONTREUIL = new Library(Type.NATIONAL,
			new Address(1, "Rue de Montreuil1", 93101, "Montreuil"), new Director("Romain", "NOEL"), Arrays.asList());
	public static final Library NATIONAL_LIBRARY_MONTREUIL2 = new Library(Type.NATIONAL,
			new Address(2, "Rue de Montreuil2", 93102, "Montreuil2"), new Director("Garfield", "LECHAT1"),
			Arrays.asList(DONQUIXOTE, ATALEOFTWOCITIES));
	public static final Library SCHOOL_LIBRARY_PARIS = new Library(Type.SCHOOL,
			new Address(3, "Rue de Paris1", 75001, "Paris1"), new Director("Romain", "NOEL"), Arrays.asList());
	public static final Library SCHOOL_LIBRARY_PARIS2 = new Library(Type.SCHOOL,
			new Address(4, "Rue de Paris2", 75002, "Paris2"), new Director("Garfield", "LECHAT2"),
			Arrays.asList(LORDOFTHERINGS, HARRYPOTTER1));
	public static final Library PUBLIC_LIBRARY_VINCENNES = new Library(Type.PUBLIC,
			new Address(5, "Rue de Vincennes", 94200, "Vincennes"), new Director("Garfield", "LECHAT3"),
			Arrays.asList(DAVINCICODE, ILIUM, LORDOFTHERINGS));
	public static final Library DUMMY_LIBRARY = new Library(null, new Address(0, "DUMMY_STREET", 0, "DUMMY_CITY"),
			new Director("DUMMY_NAME", "DUMMY_SURNAME"), Arrays.asList());

	public void setup() {
		libraryDAO.deleteAll();
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL2);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS2);
		libraryDAO.save(PUBLIC_LIBRARY_VINCENNES);
		libraryDAO.flush();
	}

	public void tearDown() {
		libraryDAO.deleteAll();
		libraryDAO.flush();
	}

	public Library createDummyLibrary() {
		return libraryDAO.saveAndFlush(DUMMY_LIBRARY);
	}

}