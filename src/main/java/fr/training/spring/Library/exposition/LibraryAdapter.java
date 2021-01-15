package fr.training.spring.library.exposition;

import java.util.ArrayList;
import java.util.List;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.book.Book;

public final class LibraryAdapter {

	private LibraryAdapter() {

	}

	public static Library transformToLibrary(final LibraryDTO libraryDTO) {

		final Director director = new Director(libraryDTO.directorDTO.surname, libraryDTO.directorDTO.name);

		final List<Book> books = transformToBooks(libraryDTO.bookDTOList);

		final Address address = new Address(libraryDTO.addressDTO.number, libraryDTO.addressDTO.street,
				libraryDTO.addressDTO.postalCode, libraryDTO.addressDTO.city);

		return new Library(null, libraryDTO.type, address, director, books);
	}

	private static List<Book> transformToBooks(final List<BookDTO> bookDTOList) {
		final List<Book> result = new ArrayList<Book>();
		for (final BookDTO bookDTO : bookDTOList) {
			if (bookDTO != null) {
				result.add(new Book(null, bookDTO.isbn, bookDTO.title, bookDTO.author, bookDTO.numberOfPage,
						bookDTO.literaryGenre));
			}
		}
		return result;
	}

	public static LibraryDTO adaptToDto(final Library library) {

		return new LibraryDTO(library.getType(), //
				new AddressDTO(library.getAddress().getNumber(), library.getAddress().getStreet(),
						library.getAddress().getPostalCode(), library.getAddress().getCity()), //
				new DirectorDTO(library.getDirector().getSurname(), library.getDirector().getName()), //
				LibraryAdapter.adaptToBookListDto(library.getBooks()));
	}

	private static List<BookDTO> adaptToBookListDto(final List<Book> books) {
		final List<BookDTO> bookDtos = new ArrayList<BookDTO>();
		for (final Book book : books) {
			bookDtos.add(new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getNumberOfPage(),
					book.getLiteraryGenre()));
		}
		return bookDtos;
	}

	public static List<LibraryDTO> adaptToDtoList(final List<Library> libraries) {
		final List<LibraryDTO> librariesDto = new ArrayList<LibraryDTO>();

		for (final Library library : libraries) {
			librariesDto.add(adaptToDto(library));
		}
		return librariesDto;
	}

}