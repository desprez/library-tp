package fr.training.spring.library;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("tp-spring-0")
class LibraryApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private LibraryDAO libraryDAO;

	public static final Library NATIONAL_LIBRARY_MONTREUIL = new Library(0L, Type.NATIONAL,
			new Address(93, "Rue des Montreuil", 93100, "Montreuil"), new Director("Romain", "NOEL"));
	public static final Library SCHOOL_LIBRARY_PARIS = new Library(0L, Type.SCHOOL,
			new Address(75, "Rue de Paris", 75008, "Paris"), new Director("Garfield", "LECHAT"));
	public static final Library PUBLIC_LIBRARY_VINCENNES = new Library(0L, Type.PUBLIC,
			new Address(94, "Rue de Vincennes", 94200, "Vincennes"), new Director("Garfield", "LECHAT"));

	// As long as we have some other integration tests, this is useless
	// @Test
	// public void contextLoads() {
	//
	// }

	@BeforeEach
	public void reset() {
		libraryDAO.deleteAll();
	}

	@Nested
	@DisplayName("Api GET:/libraries ")
	class TestReadAll {
		@Test
		@DisplayName("should return empty list when no libraries created beforehand")
		void test_read_all_1() {
			// --------------- Given ---------------
			// The DB has been reset (see method annotated with @BeforeEach)

			// --------------- When ---------------
			// I do a request on /libraries
			final ResponseEntity<Library[]> response = restTemplate.getForEntity("/libraries", Library[].class);

			// --------------- Then ---------------
			// I get an empty list and a response code 200
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isEmpty();
		}

		@Test
		@DisplayName("should return one library when 1 library was created beforehand")
		void test_read_all_2() {
			// --------------- Given ---------------
			// The DB has been reset (see method annotated with @BeforeEach)
			libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);

			// --------------- When ---------------
			// I do a request on /libraries
			final ResponseEntity<Library[]> response = restTemplate.getForEntity("/libraries", Library[].class);

			// --------------- Then ---------------
			// I get an empty list and a response code 200
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).hasSize(1);
			// TODO : Check equality
		}
	}

	@Test
	@DisplayName("Api POST:/libraries should return a status created with ID of created library when passing a correct library")
	void test_create_1() {
		// --------------- Given ---------------
		// The DB has been reset (see method annotated with @BeforeEach)

		// --------------- When ---------------
		// I do a request on /libraries
		final ResponseEntity<Long> response = restTemplate.postForEntity("/libraries", NATIONAL_LIBRARY_MONTREUIL,
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
			final Library librarySaved = libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
			final Long idOfSavedLibrary = librarySaved.getId();

			// --------------- When ---------------
			restTemplate.put("/libraries/" + idOfSavedLibrary, SCHOOL_LIBRARY_PARIS);

			// --------------- Then ---------------
			final Optional<Library> libraryFromDB = libraryDAO.findById(idOfSavedLibrary);
			assertThat(libraryFromDB).isNotEmpty();

			// TODO : Check equality
			assertThat(libraryFromDB.get().getType()).isEqualTo(SCHOOL_LIBRARY_PARIS.getType());
		}

		@Test
		@DisplayName(" should send an error when passing on an incorrect ID")
		void test_update_2() {
			// --------------- Given ---------------
			final Library librarySaved = libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
			final Long idOfSavedLibrary = librarySaved.getId();

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/libraries/" + Long.MAX_VALUE,
					HttpMethod.PUT, new HttpEntity<>(SCHOOL_LIBRARY_PARIS), String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			// assertThat(response.getBody()).contains("LIBRARY NOT FOUND");
		}
	}

	@Nested
	@DisplayName("Api DELETE:/libraries")
	class Test_delete {
		@Test
		@DisplayName(" should delete the library when passing on a correct ID")
		void test_delete_1() {
			// --------------- Given ---------------
			final Library librarySaved = libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
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
			final Library librarySaved = libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
			final Long idOfSavedLibrary = librarySaved.getId();

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/libraries/" + Long.MAX_VALUE,
					HttpMethod.DELETE, null, String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			// assertThat(response.getBody()).contains("LIBRARY NOT FOUND");
		}
	}

	@Test
	@DisplayName("Api GET:/libraries/type/{type} should return all NATIONAL libraries when passing NATIONAL as parameter")
	void test_list_with_filter_1() {
		// --------------- Given ---------------
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(PUBLIC_LIBRARY_VINCENNES);

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
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
		libraryDAO.save(NATIONAL_LIBRARY_MONTREUIL);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(SCHOOL_LIBRARY_PARIS);
		libraryDAO.save(PUBLIC_LIBRARY_VINCENNES);

		// --------------- When ---------------
		final ResponseEntity<Library[]> response = restTemplate
				.getForEntity("/libraries/director/surname/" + "Garfield", Library[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(3)
		.allMatch(library -> library.getDirector().getSurname().equals("Garfield"));
	}

}