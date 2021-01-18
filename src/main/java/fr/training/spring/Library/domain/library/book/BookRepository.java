package fr.training.spring.library.domain.library.book;

import fr.training.spring.library.domain.ddd.DDD;

@DDD.Repository
public interface BookRepository {

	Book searchBook(String isbn);

}
