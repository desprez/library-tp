package fr.training.spring.library.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Super Class off all Domain Events.
 */
public abstract class Event {

	private final UUID id;

	private final LocalDateTime createdTime;

	public Event() {
		id = UUID.randomUUID();
		createdTime = LocalDateTime.now();
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the createdTime
	 */
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

}