package fr.training.spring.library.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;

public interface LibraryDAO extends JpaRepository<LibraryJPA, Long> {

	List<Library> findLibraryByType(Type type);

	List<Library> findLibraryByDirectorSurname(String surname);

}