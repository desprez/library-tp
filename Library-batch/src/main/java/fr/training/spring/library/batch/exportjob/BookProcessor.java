package fr.training.spring.library.batch.exportjob;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.training.spring.library.domain.library.book.Book;

@Component
public class BookProcessor implements ItemProcessor<String, BookDto> {

	private static final Logger logger = LoggerFactory.getLogger(BookProcessor.class);

	@Autowired
	private CustomerService customerService;

	@Override
	public BookDto process(final String customerId) throws Exception {
		final Book customer = customerService.findOne(customerId);
		logger.info("Processing Customer {}", customer);


		return BookDto;
	}

}