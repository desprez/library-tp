package fr.training.spring.library.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.domain.library.Library;

@Component
public class LibraryProcessor implements ItemProcessor<Long, LibraryDto> {

	private static final Logger logger = LoggerFactory.getLogger(LibraryProcessor.class);

	@Autowired
	private LibraryService libraryService;

	@Override
	public LibraryDto process(final Long libraryId) throws Exception {
		final Library library = libraryService.obtain(libraryId);
		logger.info("Processing Library {}", library);

		final LibraryDto libraryDto = new LibraryDto();
		libraryDto.setId(library.getId());
		libraryDto.setType(library.getType());
		libraryDto.setAddressNumber(library.getAddress().getNumber());
		libraryDto.setAddressStreet(library.getAddress().getStreet());
		libraryDto.setAddressCity(library.getAddress().getCity());
		libraryDto.setAddressPostalCode(library.getAddress().getPostalCode());
		libraryDto.setDirectorSurname(library.getDirector().getSurname());
		libraryDto.setDirectorName(library.getDirector().getName());
		return libraryDto;
	}

}
