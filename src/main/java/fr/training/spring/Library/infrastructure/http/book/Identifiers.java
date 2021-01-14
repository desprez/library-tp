package fr.training.spring.library.infrastructure.http.book;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Identifiers {

	private List<String> goodreads;

	private List<String> librarything;

	public List<String> getGoodreads() {
		return goodreads;
	}

	public void setGoodreads(final List<String> goodreads) {
		this.goodreads = goodreads;
	}

	public List<String> getLibrarything() {
		return librarything;
	}

	public void setLibrarything(final List<String> librarything) {
		this.librarything = librarything;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) //
				.append("goodreads", getGoodreads()) //
				.append("librarything", getLibrarything()) //
				.toString();
	}

}
