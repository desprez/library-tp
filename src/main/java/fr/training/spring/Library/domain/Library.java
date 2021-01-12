package fr.training.spring.Library.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "LIBRARY")
public class Library {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	private Type type;

	@Embedded
	private Address address;

	@Embedded
	private Director director;

	public Library() {
	}

	public Library(final Long id, final Type type, final Address address, final Director director) {
		this.id = id;
		this.type = type;
		this.address = address;
		this.director = director;
	}

	public void update(final Library libraryWithNewInformation) {
		type = libraryWithNewInformation.getType();
		address = libraryWithNewInformation.getAddress();
		director = libraryWithNewInformation.getDirector();
	}

	public Long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public Address getAddress() {
		return address;
	}

	public Director getDirector() {
		return director;
	}
}