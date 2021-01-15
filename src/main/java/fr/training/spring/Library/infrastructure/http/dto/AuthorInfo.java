package fr.training.spring.library.infrastructure.http.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuthorInfo {

	private String name;
	private String personal_name;
	private DateInfo last_modified;
	private String key;
	private TypeInfo type;
	private int id;
	private int revision;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPersonal_name() {
		return personal_name;
	}

	public void setPersonal_name(final String personal_name) {
		this.personal_name = personal_name;
	}

	public DateInfo getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(final DateInfo last_modified) {
		this.last_modified = last_modified;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public TypeInfo getType() {
		return type;
	}

	public void setType(final TypeInfo type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
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
				.append("name", getName()) //
				.append("personal_name", getPersonal_name()) //
				.append("last_modified", getLast_modified()) //
				.append("key", getKey()) //
				.append("type", getType()) //
				.append("id", getId()) //
				.append("revision", getRevision()) //
				.toString();
	}


}
