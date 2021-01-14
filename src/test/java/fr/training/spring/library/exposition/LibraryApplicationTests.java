package fr.training.spring.library.exposition;

import static fr.training.spring.library.exposition.DatabaseTestHelper.NATIONAL_LIBRARY_MONTREUIL;
import static fr.training.spring.library.exposition.DatabaseTestHelper.SCHOOL_LIBRARY_PARIS;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.infrastructure.LibraryDAO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("tp-spring-0")
class LibraryApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LibraryDAO libraryDAO;

	@Autowired
	private DatabaseTestHelper databaseTestHelper;

	// As long as we have some other integration tests, this is useless
	// @Test
	// public void contextLoads() {
	//
	// }

	@BeforeEach
	public void setupTestData() {
		databaseTestHelper.setup();
	}

	@AfterEach
	public void tearDown() {
		databaseTestHelper.tearDown();
	}

	@Test
	@DisplayName("Api GET:/libraries should return all 5 libraries")
	void test_read_all() {
		// --------------- Given ---------------
		// Test data

		// --------------- When ---------------
		// I do a request on /libraries
		final ResponseEntity<Library[]> response = restTemplate.getForEntity("/libraries", Library[].class);

		// --------------- Then ---------------
		// I get an list of all libraries and a response code 200
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull().hasSize(5)
		.anyMatch(library -> library.getBooks().size() == 2 && library.getType() == Type.NATIONAL);
		assertThat(Arrays.stream(response.getBody()).flatMap(library -> library.getBooks().stream()))
		.doesNotHaveDuplicates();
		// Attention here ! If you try to add the same object multiple times in a
		// one-to-many, it will MOVE the object (and not duplicate it)
		// .haveAtMost(1, new Condition<>(book ->
		// book.getTitle().equals(LORDOFTHERINGS.getTitle()), ""));
	}

	@Test
	@DisplayName("Api GET:/libraries/{libraryId} should return the good one library")
	void test_read_one() {
		// --------------- Given ---------------
		final Library dummyLibrary = databaseTestHelper.createDummyLibrary();

		// --------------- When ---------------
		// I do a request on /libraries/ + existing id
		final ResponseEntity<Library> response = restTemplate.getForEntity("/libraries/" + dummyLibrary.getId(),
				Library.class);

		// --------------- Then ---------------
		// I get a library and a response code 200
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getId()).isEqualTo(dummyLibrary.getId());
		assertThat(response.getBody().getBooks().size()).isEqualTo(dummyLibrary.getBooks().size());
	}

	@Test
	@DisplayName("Api POST:/libraries should return a status created with ID of created library when passing a correct library")
	void test_create_1() {
		// --------------- Given ---------------
		// Test data

		// --------------- When ---------------
		// I do a request on /libraries
		final LibraryDTO mantional_library_montreuil_dto = new LibraryDTO(NATIONAL_LIBRARY_MONTREUIL.getType(),
				new AddressDTO(NATIONAL_LIBRARY_MONTREUIL.getAddress().getNumber(),
						NATIONAL_LIBRARY_MONTREUIL.getAddress().getStreet(),
						NATIONAL_LIBRARY_MONTREUIL.getAddress().getPostalCode(),
						NATIONAL_LIBRARY_MONTREUIL.getAddress().getCity()),
				new DirectorDTO(
						NATIONAL_LIBRARY_MONTREUIL.getDirector().getSurname(), NATIONAL_LIBRARY_MONTREUIL.getDirector()
						.getName()),
				NATIONAL_LIBRARY_MONTREUIL.getBooks().stream().map(book -> new BookDTO(book.getIsbn(), book.getTitle(),
						book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre()))
				.collect(Collectors.toList()));
		final ResponseEntity<Long> response = restTemplate.postForEntity("/libraries", mantional_library_montreuil_dto,
				Long.class);

		// --------------- Then ---------------
		// I get a success code, and a new library in the database with the given ID
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		final Long idCreated = response.getBody();
		assertThat(idCreated).isNotNull().isPositive();

		final Optional<Library> libraryFromDB = libraryDAO.findById(idCreated);
		assertThat(libraryFromDB).isNotEmpty();

		// Due to equals method not being implemented, we would need to compare field by
		// fields...which is bad !
		// We'll talk about equality in DDD further in this course.
		// TODO : Check equality
		assertThat(libraryFromDB.get().getType()).isEqualTo(NATIONAL_LIBRARY_MONTREUIL.getType());
	}

	@Nested
	@DisplayName("Api PUT:/libraries")
	class Test_update {
		@Test
		@DisplayName(" should update the library when passing on a correct ID")
		void test_update_1() {
			// --------------- Given ---------------
			final Library dummyLibrary = databaseTestHelper.createDummyLibrary();
			final Long idOfCreatedLibrary = dummyLibrary.getId();

			// --------------- When ---------------
			restTemplate.put("/libraries/" + idOfCreatedLibrary, SCHOOL_LIBRARY_PARIS);

			// --------------- Then ---------------
			final Optional<Library> libraryFromDB = libraryDAO.findById(idOfCreatedLibrary);
			assertThat(libraryFromDB).isNotEmpty();

			// TODO : Check equality
			assertThat(libraryFromDB.get().getType()).isEqualTo(SCHOOL_LIBRARY_PARIS.getType());
		}

		@Test
		@DisplayName(" should send an error when passing on an incorrect ID")
		void test_update_2() {
			// --------------- Given ---------------
			// Test data

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/libraries/" + Long.MAX_VALUE,
					HttpMethod.PUT, new HttpEntity<>(SCHOOL_LIBRARY_PARIS), String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(response.getBody()).contains(ErrorCodes.LIBRARY_NOT_FOUND);
		}
	}

	@Nested
	@DisplayName("Api DELETE:/libraries")
	class Test_delete {
		@Test
		@DisplayName(" should delete the library when passing on a correct ID")
		void test_delete_1() {
			// --------------- Given ---------------
			final Library librarySaved = databaseTestHelper.createDummyLibrary();
			final Long idOfSavedLibrary = librarySaved.getId();

			// --------------- When ---------------
			restTemplate.delete("/libraries/" + idOfSavedLibrary);

			// --------------- Then ---------------
			final Optional<Library> libraryFromDB = libraryDAO.findById(idOfSavedLibrary);
			assertThat(libraryFromDB).isEmpty();
		}

		@Test
		@DisplayName(" should send an error when passing on an incorrect ID")
		void test_delete_2() {
			// --------------- Given ---------------
			// Test data

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/libraries/" + Long.MAX_VALUE,
					HttpMethod.DELETE, null, String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(response.getBody()).contains(ErrorCodes.LIBRARY_NOT_FOUND);
		}
	}

	@Test
	@DisplayName("Api GET:/libraries/type/{type} should return all NATIONAL libraries when passing NATIONAL as parameter")
	void test_list_with_filter_1() {
		// --------------- Given ---------------
		// Test data

		// --------------- When ---------------
		final ResponseEntity<Library[]> response = restTemplate.getForEntity("/libraries/type/" + Type.NATIONAL,
				Library[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(2).allMatch(library -> library.getType().equals(Type.NATIONAL));
	}

	@Test
	@DisplayName("Api GET:/libraries/director/surname/{surname} should get all libraries ruled by Garfield when passing Garfield as parameter")
	void test_list_with_filter_2() {
		// --------------- Given ---------------
		// Test data

		// --------------- When ---------------
		final ResponseEntity<Library[]> response = restTemplate
				.getForEntity("/libraries/director/surname/" + "Garfield", Library[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(3)
		.allMatch(library -> library.getDirector().getSurname().equals("Garfield"));
	}
}