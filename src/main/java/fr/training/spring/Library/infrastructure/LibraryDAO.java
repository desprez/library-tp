package fr.training.spring.Library.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.spring.Library.domain.Library;
import fr.training.spring.Library.domain.Type;

public interface LibraryDAO extends JpaRepository<Library, Long> {

	List<Library> findLibraryByType(Type type);

	List<Library> findLibraryByDirectorSurname(String surname);
}