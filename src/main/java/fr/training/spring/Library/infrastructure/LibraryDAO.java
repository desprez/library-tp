package fr.training.spring.library.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.spring.library.domain.Library;
import fr.training.spring.library.domain.Type;

public interface LibraryDAO extends JpaRepository<Library, Long> {

	List<Library> findLibraryByType(Type type);

	List<Library> findLibraryByDirectorSurname(String surname);

}