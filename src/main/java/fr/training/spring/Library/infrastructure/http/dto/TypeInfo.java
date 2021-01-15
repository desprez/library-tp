package fr.training.spring.library.infrastructure.http.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TypeInfo {

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		final ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		toStringBuilder.append("key", getKey());
		return toStringBuilder.toString();
	}

}
