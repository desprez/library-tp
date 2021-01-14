package fr.training.spring.library.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.infrastructure.LibraryDAO;

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
		return libraryDAO.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not obtain library " + id, ErrorCodes.LIBRARY_NOT_FOUND));
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