package fr.training.spring.Library.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.Library.domain.Library;
import fr.training.spring.Library.domain.Type;
import fr.training.spring.Library.domain.exception.LibraryNotFoundException;
import fr.training.spring.Library.infrastructure.LibraryDAO;

@Transactional
@Service
public class LibraryService {

	@Autowired
	private LibraryDAO libraryDAO;

	public Long create(final Library newLibrary) {
		final Library library = libraryDAO.save(newLibrary);
		return library.getId();
	}

	public Library obtain(final Long id) {
		return libraryDAO.findById(id).orElseThrow(() -> new LibraryNotFoundException("Could not obtain library "+id));
	}

	public List<Library> listAll() {
		return libraryDAO.findAll();
	}

	public void update(final Long id, final Library libraryWithNewInformations) {
		final Library library = obtain(id);
		library.update(libraryWithNewInformations);
		libraryDAO.save(library);
	}

	public void remove(final Long id) {
		final Library library = obtain(id);
		libraryDAO.delete(library);
	}

	public List<Library> listAllByType(final Type type) {
		return libraryDAO.findLibraryByType(type);
	}

	public List<Library> listAllByDirectorName(final String surname) {
		return libraryDAO.findLibraryByDirectorSurname(surname);
	}
}