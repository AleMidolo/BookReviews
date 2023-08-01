package org;

import java.util.ArrayList;
import java.util.List;

public class Author {

	private String fullName;
	private List<Book> books;
	
	public Author(String fullName) {
		this.fullName = fullName;
		books = new ArrayList<>();
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void addBook(Book b) {
		if(!books.contains(b))
			books.add(b);
	}
}