package fr.training.spring.library.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.LibraryRepository;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.BookRepository;
import fr.training.spring.library.domain.library.book.LiteraryGenre;

@DDD.ApplicationService
@Transactional
@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private BookRepository bookRepository;

	public Long create(final Library newLibrary) {
		return libraryRepository.save(newLibrary);
	}

	public Library obtain(final Long id) {
		return libraryRepository.get(id);
	}

	public List<Library> listAll() {
		return libraryRepository.findAll();
	}

	public void update(final Long id, final Library libraryWithNewInformations) {
		final Library library = obtain(id);
		library.update(libraryWithNewInformations);
		libraryRepository.save(library);
	}

	public void remove(final Long id) {
		final Library library = obtain(id);
		libraryRepository.delete(library);
	}

	public List<Library> listAllByType(final Type type) {
		return libraryRepository.findLibraryByType(type);
	}

	public List<Library> listAllByDirectorName(final String surname) {
		return libraryRepository.findLibraryByDirectorSurname(surname);
	}

	public void referenceBook(final Long libraryId, final String isbn, final LiteraryGenre literaryGenre) {
		final Book book = bookRepository.searchBook(isbn );
		book.assignLiteraryGenre(literaryGenre);

		final Library library = obtain(libraryId);
		library.addBook(book);
		libraryRepository.save(library);
	}

}