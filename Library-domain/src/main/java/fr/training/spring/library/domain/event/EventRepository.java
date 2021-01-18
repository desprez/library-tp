package fr.training.spring.library.domain.event;

/**
 * Repository used to publish all events
 */
public interface EventRepository {

	public void publish(final Event event);

}
