package fr.training.spring.Library;

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

@RestController
public class LibraryResource {

	@Autowired
	private LibraryService libraryService;

	@PostMapping("/libraries")
	@ResponseStatus(HttpStatus.CREATED)
	public Long createLibrary(@RequestBody final Library library) {
		return libraryService.create(library);
	}

	@GetMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.OK)
	public Library detailLibrary(@PathVariable("libraryId") final Long libraryId) {
		return libraryService.obtain(libraryId);
	}

	@GetMapping("/libraries")
	@ResponseStatus(HttpStatus.OK)
	public List<Library> listAllLibrairies() {
		return libraryService.listAll();
	}

	@PutMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateLibrary(@PathVariable("libraryId") final Long libraryId, @RequestBody final Library library) {
		libraryService.update(libraryId, library);
	}

	@DeleteMapping("/libraries/{libraryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeLibrary(@PathVariable("libraryId") final Long libraryId) {
		libraryService.remove(libraryId);
	}

	@GetMapping("/libraries/type/{type}")
	@ResponseStatus(HttpStatus.OK)
	public List<Library> listAllLibrairiesByType(@PathVariable("type") final Type type) {
		return libraryService.listAllByType(type);
	}

	@GetMapping("/libraries/director/surname/{surname}")
	@ResponseStatus(HttpStatus.OK)
	public List<Library> listAllLibrairiesByDirectorName(@PathVariable("surname") final String surname) {
		return libraryService.listAllByDirectorName(surname);
	}
}