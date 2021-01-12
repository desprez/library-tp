package fr.training.spring.Library.domain.library;

import java.util.List;

import fr.training.spring.Library.domain.ddd.DDD;

@DDD.Repository
public interface LibraryRepository {

	Long save(Library library);

	Library get(Long id);

	List<Library> findAll();

	void delete(Library library);

	List<Library> findLibraryByType(Type type);

	List<Library> findLibraryByDirectorSurname(String surname);
}