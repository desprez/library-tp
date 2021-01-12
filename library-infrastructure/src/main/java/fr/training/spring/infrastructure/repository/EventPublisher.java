
package fr.training.spring.infrastructure.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fr.training.spring.domain.event.Event;
import fr.training.spring.domain.event.EventRepository;

@Component
public class EventPublisher implements EventRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

	public void publish(final Event event) {
		LOGGER.debug("Publish Event # {}", event);
	}

}
