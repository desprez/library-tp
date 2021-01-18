package fr.training.spring.library.batch.exportjob;

import fr.training.spring.library.domain.library.book.LiteraryGenre;

public class BookDto {

	public String isbn;

	public String title;

	public String author;

	public int numberOfPage;

	public LiteraryGenre literaryGenre;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(final int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	public LiteraryGenre getLiteraryGenre() {
		return literaryGenre;
	}

	public void setLiteraryGenre(final LiteraryGenre literaryGenre) {
		this.literaryGenre = literaryGenre;
	}



}
