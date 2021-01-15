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
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.infrastructure.LibraryDAO;
import fr.training.spring.library.infrastructure.LibraryJPA;

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
		final ResponseEntity<LibraryDTO[]> response = restTemplate.getForEntity("/libraries", LibraryDTO[].class);

		// --------------- Then ---------------
		// I get an list of all libraries and a response code 200
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull().hasSize(5)
		.anyMatch(library -> library.bookDTOList.size() == 2 && library.type == Type.NATIONAL);
		assertThat(Arrays.stream(response.getBody()).flatMap(library -> library.bookDTOList.stream()))
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
		final LibraryJPA dummyLibrary = databaseTestHelper.createDummyLibrary();

		// --------------- When ---------------
		// I do a request on /libraries/ + existing id
		final ResponseEntity<LibraryDTO> response = restTemplate.getForEntity("/libraries/" + dummyLibrary.getId(),
				LibraryDTO.class);

		// --------------- Then ---------------
		// I get a library and a response code 200
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// assertThat(response.getBody().getId()).isEqualTo(dummyLibrary.getId());
		assertThat(response.getBody().bookDTOList.size()).isEqualTo(dummyLibrary.getBooks().size());
	}

	@Nested
	@DisplayName("Api POST:/libraries ")
	class test_create {

		@Test
		@DisplayName("should return a status created with ID of created library when passing a correct library")
		void test_create_1() {
			// --------------- Given ---------------
			final LibraryDTO national_library_montreuil_dto = new LibraryDTO(NATIONAL_LIBRARY_MONTREUIL.getType(),
					new AddressDTO(NATIONAL_LIBRARY_MONTREUIL.getAddress().getNumber(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getStreet(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getPostalCode(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getCity()),
					new DirectorDTO(NATIONAL_LIBRARY_MONTREUIL.getDirector().getSurname(),
							NATIONAL_LIBRARY_MONTREUIL.getDirector().getName()),
					NATIONAL_LIBRARY_MONTREUIL
					.getBooks().stream().map(book -> new BookDTO(book.getIsbn(), book.getTitle(),
							book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre()))
					.collect(Collectors.toList()));

			// --------------- When ---------------
			// I do a request on /libraries
			final ResponseEntity<Long> response = restTemplate.postForEntity("/libraries",
					national_library_montreuil_dto, Long.class);

			// --------------- Then ---------------
			// I get a success code, and a new library in the database with the given ID
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			final Long idCreated = response.getBody();
			assertThat(idCreated).isNotNull().isPositive();

			final Optional<LibraryJPA> libraryFromDB = libraryDAO.findById(idCreated);
			assertThat(libraryFromDB).isNotEmpty();

			// Due to equals method not being implemented, we would need to compare field by
			// fields...which is bad !
			// We'll talk about equality in DDD further in this course.
			// TODO : Check equality
			assertThat(libraryFromDB.get().getType()).isEqualTo(NATIONAL_LIBRARY_MONTREUIL.getType());
		}

		@Test
		@DisplayName(" should return an error when creating a library with no director")
		void test_create_2() {
			// --------------- Given ---------------
			final LibraryDTO national_library_montreuil_dto = new LibraryDTO(NATIONAL_LIBRARY_MONTREUIL.getType(),
					new AddressDTO(NATIONAL_LIBRARY_MONTREUIL.getAddress().getNumber(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getStreet(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getPostalCode(),
							NATIONAL_LIBRARY_MONTREUIL.getAddress().getCity()),
					null,
					NATIONAL_LIBRARY_MONTREUIL
					.getBooks().stream().map(book -> new BookDTO(book.getIsbn(), book.getTitle(),
							book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre()))
					.collect(Collectors.toList()));

			// --------------- When ---------------
			// I do a request on /libraries
			final ResponseEntity<String> response = restTemplate.postForEntity("/libraries",
					national_library_montreuil_dto, String.class);

			// --------------- Then ---------------
			// I get a success code, and a new library in the database with the given ID
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(response.getBody()).isNotNull().contains(ErrorCodes.LIBRARY_MUST_HAVE_A_DIRECTOR);
		}
	}

	@Nested
	@DisplayName("Api PUT:/libraries")
	class Test_update {
		@Test
		@DisplayName(" should update the library when passing on a correct ID")
		void test_update_1() {
			// --------------- Given ---------------
			final LibraryJPA dummyLibrary = databaseTestHelper.createDummyLibrary();
			final Long idOfCreatedLibrary = dummyLibrary.getId();

			// --------------- When ---------------

			final LibraryDTO schoolLibraryParisDTO = new LibraryDTO(SCHOOL_LIBRARY_PARIS.getType(), new AddressDTO(
					SCHOOL_LIBRARY_PARIS.getAddress().getNumber(), SCHOOL_LIBRARY_PARIS.getAddress().getStreet(),
					SCHOOL_LIBRARY_PARIS.getAddress().getPostalCode(), SCHOOL_LIBRARY_PARIS.getAddress().getCity()),
					new DirectorDTO(SCHOOL_LIBRARY_PARIS.getDirector().getName(),
							SCHOOL_LIBRARY_PARIS.getDirector().getSurname()),
					SCHOOL_LIBRARY_PARIS
					.getBooks().stream().map(book -> new BookDTO(book.getIsbn(), book.getTitle(),
							book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre()))
					.collect(Collectors.toList()));

			restTemplate.put("/libraries/" + idOfCreatedLibrary, schoolLibraryParisDTO);

			// --------------- Then ---------------
			final Optional<LibraryJPA> libraryFromDB = libraryDAO.findById(idOfCreatedLibrary);
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

			final LibraryDTO schoolLibraryParisDTO = new LibraryDTO(SCHOOL_LIBRARY_PARIS.getType(), //
					new AddressDTO(SCHOOL_LIBRARY_PARIS.getAddress().getNumber(),
							SCHOOL_LIBRARY_PARIS.getAddress().getStreet(),
							SCHOOL_LIBRARY_PARIS.getAddress().getPostalCode(),
							SCHOOL_LIBRARY_PARIS.getAddress().getCity()),
					new DirectorDTO(SCHOOL_LIBRARY_PARIS.getDirector().getName(),
							SCHOOL_LIBRARY_PARIS.getDirector().getSurname()),
					SCHOOL_LIBRARY_PARIS
					.getBooks().stream().map(book -> new BookDTO(book.getIsbn(), book.getTitle(),
							book.getAuthor(), book.getNumberOfPage(), book.getLiteraryGenre()))
					.collect(Collectors.toList()));

			final ResponseEntity<String> response = restTemplate.exchange("/libraries/" + Long.MAX_VALUE,
					HttpMethod.PUT, new HttpEntity<>(schoolLibraryParisDTO), String.class);

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
			final LibraryJPA librarySaved = databaseTestHelper.createDummyLibrary();
			final Long idOfSavedLibrary = librarySaved.getId();

			// --------------- When ---------------
			restTemplate.delete("/libraries/" + idOfSavedLibrary);

			// --------------- Then ---------------
			final Optional<LibraryJPA> libraryFromDB = libraryDAO.findById(idOfSavedLibrary);
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
		final ResponseEntity<LibraryDTO[]> response = restTemplate.getForEntity("/libraries/type/" + Type.NATIONAL,
				LibraryDTO[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(2).allMatch(library -> library.type.equals(Type.NATIONAL));
	}

	@Test
	@DisplayName("Api GET:/libraries/director/surname/{surname} should get all libraries ruled by Garfield when passing Garfield as parameter")
	void test_list_with_filter_2() {
		// --------------- Given ---------------
		// Test data

		// --------------- When ---------------
		final ResponseEntity<LibraryDTO[]> response = restTemplate
				.getForEntity("/libraries/director/surname/" + "Garfield", LibraryDTO[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(3).allMatch(library -> library.directorDTO.surname.equals("Garfield"));
	}
}