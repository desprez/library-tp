package fr.training.spring.library.exposition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.domain.library.Type;

@RestController
public class LibraryResource {

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/libraries")
	@ResponseStatus(HttpStatus.CREATED)
	public Long createLibrary(@RequestBody final LibraryDTO libraryDTO) {
		return libraryService.create(LibraryAdapter.transformToLibrary(libraryDTO));
	}

	@GetMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.OK)
	public LibraryDTO detailLibrary(@PathVariable("libraryId") final Long libraryId) {
		return LibraryAdapter.adaptToDto(libraryService.obtain(libraryId));
	}

	@GetMapping("/libraries")
	@ResponseStatus(HttpStatus.OK)
	public List<LibraryDTO> listAllLibrairies() {
		return LibraryAdapter.adaptToDtoList(libraryService.listAll());
	}

	@PutMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateLibrary(@PathVariable("libraryId") final Long libraryId,
			@RequestBody final LibraryDTO libraryDTO) {
		libraryService.update(libraryId, LibraryAdapter.transformToLibrary(libraryDTO));
	}

	@DeleteMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeLibrary(@PathVariable("libraryId") final Long libraryId) {
		libraryService.remove(libraryId);
	}

	@GetMapping("/libraries/type/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<LibraryDTO> listAllLibrairiesByType(@PathVariable("type") final Type type) {
		return LibraryAdapter.adaptToDtoList(libraryService.listAllByType(type));
	}

	@GetMapping("/libraries/director/surname/{surname}")
	@ResponseStatus(HttpStatus.OK)
	public List<LibraryDTO> listAllLibrairiesByDirectorName(@PathVariable("surname") final String surname) {
		return LibraryAdapter.adaptToDtoList(libraryService.listAllByDirectorName(surname));
	}

}