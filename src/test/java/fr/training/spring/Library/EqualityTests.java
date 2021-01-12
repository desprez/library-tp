package fr.training.spring.Library;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import fr.training.spring.Library.domain.library.Address;
import fr.training.spring.Library.domain.library.Director;
import fr.training.spring.Library.domain.library.Library;
import fr.training.spring.Library.domain.library.Type;

public class EqualityTests {

	@Test
	public void entities_should_be_equal_if_same_identity() {
		final Address address = new Address(12, "rue Alberty", 75000, "Paris");

		final Director director = new Director("surname", "name");

		final Library l1 = new Library(1L, Type.ASSOCIATIVE, address, director, null);
		final Library l2 = new Library(1L, Type.NATIONAL, address, director, null);

		assertThat(l1).isEqualTo(l2);
	}

	@Test
	public void value_objets_should_be_equal_if_same_properties() {
		final Director d1 = new Director("surname1", "name1");
		final Director d2 = new Director("surname1", "name1");

		assertThat(d1).isEqualTo(d2);
	}
}
