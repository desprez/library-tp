package fr.training.spring.library.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.Type;

public interface LibraryDAO extends JpaRepository<Library, Long> {

	List<Library> findByType(Type type);

	List<Library> findByDirector_Surname(String surname);

	@Query("SELECT l FROM Library l WHERE l.director.surname = ?1")
	List<Library> searchByDirectorNameQuery(String surname);

	@Query(value = "SELECT * FROM LIBRARY WHERE DIRECTOR_SURNAME = :surname", nativeQuery = true)
	List<Library> searchByDirectorNameNativeQuery(String surname);
}