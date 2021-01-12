package fr.training.spring.Library.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.training.spring.Library.domain.ddd.DDD;
import fr.training.spring.Library.domain.exception.ErrorCodes;
import fr.training.spring.Library.domain.exception.LibraryNotFoundException;
import fr.training.spring.Library.domain.library.Library;
import fr.training.spring.Library.domain.library.LibraryRepository;
import fr.training.spring.Library.domain.library.Type;

@DDD.RepositoryImpl
@Repository
public class LibraryRepositoryImpl implements LibraryRepository {

	@Autowired
	private LibraryDAO libraryDAO;

	@Override
	public Library get(final Long id) {
		return libraryDAO.findById(id).map(LibraryJPA::toLibrary).orElseThrow(() -> new LibraryNotFoundException(ErrorCodes.LIBRARY_NOT_FOUND));
	}

	@Override
	public Long save(final Library library) {
		final LibraryJPA libraryJPA = libraryDAO.save(new LibraryJPA(library));
		return libraryJPA.getId();
	}

	@Override
	public List<Library> findAll() {
		return libraryDAO.findAll().stream().map(LibraryJPA::toLibrary).collect(Collectors.toList());
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