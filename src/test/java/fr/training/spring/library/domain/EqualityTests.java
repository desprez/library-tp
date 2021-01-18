package fr.training.spring.library.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.Type;
import fr.training.spring.library.domain.library.book.Book;
import fr.training.spring.library.domain.library.book.LiteraryGenre;

public class EqualityTests {

	@Test
	public void entities_should_be_equal_if_same_identity() {
		final Library l1 = new Library(1L, Type.ASSOCIATIVE, new Address(1, "Rue de Paris", 91000, "Montreuil"),
				new Director("Jean", "Martin"), null);

		final Library l2 = new Library(1L, Type.ASSOCIATIVE, new Address(1, "Rue de Paris", 91000, "Montreuil"),
				new Director("Jean", "Martin"), null);

		assertThat(l1).isEqualTo(l2);


		final Book  b1 = new Book(1L, "isbn", "title", "author", 100, LiteraryGenre.COMEDY);

		final Book  b2 = new Book(1L, "isbn", "title", "author", 100, LiteraryGenre.COMEDY);

		assertThat(b1).isEqualTo(b2);
	}

	@Test
	public void value_objets_should_be_equal_if_same_properties() {

		final Address a1 = new Address(1, "Rue de Paris", 91000, "Montreuil");

		final Address a2 = new Address(1, "Rue de Paris", 91000, "Montreuil");

		assertThat(a1).isEqualTo(a2);


		final Director d1 = new Director("Jean", "Martin");
		final Director d2 = new Director("Jean", "Martin");

		assertThat(d1).isEqualTo(d2);



	}
}
