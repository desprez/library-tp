package fr.training.spring.library.domain.library;

import java.util.List;

public interface LibraryRepository {

	Long save(Library library);

	Library get(Long id);

	List<Library> findAll();

	void delete(Library library);

	List<Library> findLibraryByType(Type type);

	List<Library> findLibraryByDirectorSurname(String surname);
}