package fr.training.spring.library.infrastructure.http.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DateInfo {

	private String type;

	private String value;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
				.append("type", getType()) //
				.append("value", getValue()) //
				.toString();
	}

}
