package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ExtractData {
	
	private List<Book> books;
	private List<Review> reviews;
	
	public ExtractData(List<Book> books, List<Review> reviews) {
		this.books = books;
		this.reviews = reviews;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public List<Author> getAuthors() {
		System.out.println("getAuthors");
		List<Author> authors = new ArrayList<>();
		
		books.forEach(b -> {
			String authorsNames = b.getAuthors().replace("\"", "");
			String[] splitted = authorsNames.split(",");
			
			for(String s : splitted) {
				s = s.replace("'", "");
				s = s.replace("[", "");
				s = s.replace("]", "");
				if(s.length() > 1) {
					if(s.charAt(0) == ' ')
						s = s.substring(1, s.length()-1);
				}
				Optional<Author> opt = checkAuthor(authors, b.getAuthors());
				if(opt.isPresent())
					opt.get().addBook(b);
				else {
					Author author = new Author(s);
					author.addBook(b);
					authors.add(author);
				}
			}
		});
		
		return authors;
	}
	
	public Optional<Author> checkAuthor(List<Author> list, String name) {
		return list.stream().
			filter(a -> a.getFullName().equals(name)).
			findFirst();
	}
	
	public List<Book> getBooksWithHighestScore() {
		System.out.println("getBooksWithHighestScore");
		List<Book> listOfBooks = new ArrayList<>();
		reviews.forEach(r -> {
			if(r.getScore().equals("5.0")) {
				Optional<Book> book = books.stream().filter(b -> b.getTitle().equals(r.getTitle())).findFirst();
				if(book.isPresent() && !listOfBooks.contains(book.get()))
					listOfBooks.add(book.get());
			}
		});
		return listOfBooks;
	}
	
	public HashMap<Book, Integer> getReviewsForBook() {
		System.out.println("getReviewsForBook");
		HashMap<Book, Integer> map = new HashMap<>();
		reviews.forEach(r -> {
			Optional<Book> book = books.stream().filter(b -> b.getTitle().equals(r.getTitle())).findFirst();
			if(book.isPresent()) {
				if(map.keySet().contains(book.get())) {
					Integer val = map.get(book.get());
					val++;
					book.get().setNumberOfReviews(val);
					map.put(book.get(), val);
				}
				else {
					book.get().setNumberOfReviews(1);
					map.put(book.get(), 1);
				}
			}
		});
		
		return map;
	}
	
	public Optional<Book> getMostReviewedBook() {
		System.out.println("getMostReviewedBooks");
		HashMap<Book, Integer> reviewsForBook = getReviewsForBook();
		OptionalInt maximum = reviewsForBook.values().stream().
				mapToInt(i -> i).
				max();
		
		if(maximum.isPresent())
			return reviewsForBook.keySet().stream().
					filter(k -> reviewsForBook.get(k).equals(maximum.getAsInt())).
					findFirst();
		
		return Optional.empty();
	}
	
	public Optional<Book> getLeastReviewedBook() {
		System.out.println("getLeastReviewedBooks");
		HashMap<Book, Integer> reviewsForBook = getReviewsForBook();
		OptionalInt minimum = reviewsForBook.values().stream().
				mapToInt(i -> i).
				min();
		
		if(minimum.isPresent())
			return reviewsForBook.keySet().stream().
					filter(k -> reviewsForBook.get(k).equals(minimum.getAsInt())).
					findFirst();
		
		return Optional.empty();
	}
	
	public Optional<Book> getAverageReviewedBook() {
		System.out.println("getAverageReviewedBook");
		HashMap<Book, Integer> reviewsForBook = getReviewsForBook();
		OptionalDouble average = reviewsForBook.values().stream().
				mapToInt(i -> i).
				average();
		
		if(average.isPresent())
			return reviewsForBook.keySet().stream().
					filter(k -> reviewsForBook.get(k).equals((int)average.getAsDouble())).
					findFirst();
		
		return Optional.empty();
	}
}
