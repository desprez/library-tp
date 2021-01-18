package fr.training.spring.library.infrastructure.http.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BookInfo {

	private List<String> publishers;

	private int number_of_pages;

	private String subtitle;

	private String weight;

	private List<Integer> covers;

	private String physical_format;

	private DateInfo last_modified;

	private int latest_revision;

	private String key;

	private List<TypeInfo> authors;

	private String ocaid;

	private List<String> subjects;

	private String edition_name;

	private List<TypeInfo> languages;

	private String title;

	private Identifiers identifiers;

	private DateInfo created;

	private List<String> isbn_13;

	private List<String> isbn_10;

	private String publish_date;

	private List<TypeInfo> works;

	private TypeInfo type;

	private String physical_dimensions;

	private int revision;

	public List<String> getPublishers() {
		return publishers;
	}

	public void setPublishers(final List<String> publishers) {
		this.publishers = publishers;
	}

	public int getNumber_of_pages() {
		return number_of_pages;
	}

	public void setNumber_of_pages(final int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(final String subtitle) {
		this.subtitle = subtitle;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(final String weight) {
		this.weight = weight;
	}

	public List<Integer> getCovers() {
		return covers;
	}

	public void setCovers(final List<Integer> covers) {
		this.covers = covers;
	}

	public String getPhysical_format() {
		return physical_format;
	}

	public void setPhysical_format(final String physical_format) {
		this.physical_format = physical_format;
	}

	public DateInfo getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(final DateInfo last_modified) {
		this.last_modified = last_modified;
	}

	public int getLatest_revision() {
		return latest_revision;
	}

	public void setLatest_revision(final int latest_revision) {
		this.latest_revision = latest_revision;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public List<TypeInfo> getAuthors() {
		return authors;
	}

	public void setAuthors(final List<TypeInfo> authors) {
		this.authors = authors;
	}

	public String getOcaid() {
		return ocaid;
	}

	public void setOcaid(final String ocaid) {
		this.ocaid = ocaid;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(final List<String> subjects) {
		this.subjects = subjects;
	}

	public String getEdition_name() {
		return edition_name;
	}

	public void setEdition_name(final String edition_name) {
		this.edition_name = edition_name;
	}

	public List<TypeInfo> getLanguages() {
		return languages;
	}

	public void setLanguages(final List<TypeInfo> languages) {
		this.languages = languages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Identifiers getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(final Identifiers identifiers) {
		this.identifiers = identifiers;
	}

	public DateInfo getCreated() {
		return created;
	}

	public void setCreated(final DateInfo created) {
		this.created = created;
	}

	public List<String> getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(final List<String> isbn_13) {
		this.isbn_13 = isbn_13;
	}

	public List<String> getIsbn_10() {
		return isbn_10;
	}

	public void setIsbn_10(final List<String> isbn_10) {
		this.isbn_10 = isbn_10;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(final String publish_date) {
		this.publish_date = publish_date;
	}

	public List<TypeInfo> getWorks() {
		return works;
	}

	public void setWorks(final List<TypeInfo> works) {
		this.works = works;
	}

	public TypeInfo getType() {
		return type;
	}

	public void setType(final TypeInfo type) {
		this.type = type;
	}

	public String getPhysical_dimensions() {
		return physical_dimensions;
	}

	public void setPhysical_dimensions(final String physical_dimensions) {
		this.physical_dimensions = physical_dimensions;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(final int revision) {
		this.revision = revision;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE) //
				.append("publishers", getPublishers()) //
				.append("number_of_pages", getNumber_of_pages()) //
				.append("subtitle", getSubtitle()) //
				.append("weight", getWeight()) //
				.append("covers", getCovers()) //
				.append("physical_format", getPhysical_format()) //
				.append("last_modified", getLast_modified()) //
				.append("latest_revision", getLatest_revision()) //
				.append("key", getKey()) //
				.append("authors", getAuthors()) //
				.append("ocaid", getOcaid()) //
				.append("subjects", getSubjects()) //
				.append("edition_name", getEdition_name()) //
				.append("languages", getLanguages()) //
				.append("title", getTitle()) //
				.append("identifiers", getIdentifiers()) //
				.append("created", getCreated()) //
				.append("isbn_13", getIsbn_13()) //
				.append("isbn_10", getIsbn_10()) //
				.append("publish_date", getPublish_date()) //
				.append("works", getWorks()) //
				.append("type", getType()) //
				.append("physical_dimensions", getPhysical_dimensions()) //
				.append("revision", getRevision()) //
				.toString();
	}

}
