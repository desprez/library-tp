package fr.training.spring.library.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.LibraryRepository;
import fr.training.spring.library.domain.library.Type;

@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

	@Autowired
	private LibraryDAO libraryDAO;

	@Override
	public Long save(final Library library) {
		final LibraryJPA libraryJPA = libraryDAO.save(new LibraryJPA(library));
		return libraryJPA.getId();
	}

	@Override
	public Library get(final Long id) {
		return libraryDAO.findById(id).map(LibraryJPA::toLibrary).orElseThrow(
				() -> new NotFoundException("Could not obtain library " + id, ErrorCodes.LIBRARY_NOT_FOUND));
	}

	@Override
	public List<Library> findAll() {
		final List<LibraryJPA> libraryJPAs = libraryDAO.findAll();
		final List<Library> result = new ArrayList<Library>();
		for (final LibraryJPA libraryJPA : libraryJPAs) {
			result.add(libraryJPA.toLibrary());
		}
		return result;
	}

	@Override
	public void delete(final Library library) {
		libraryDAO.delete(new LibraryJPA(library));
	}

	@Override
	public List<Library> findLibraryByType(final Type type) {
		return libraryDAO.findLibraryByType(type);
	}

	@Override
	public List<Library> findLibraryByDirectorSurname(final String surname) {
		return libraryDAO.findLibraryByDirectorSurname(surname);
	}

}
