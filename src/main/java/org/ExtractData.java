package org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;

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
	
	public HashMap<String, Author> getAuthorsHash() {
		System.out.println("getAuthors");
		long startTime = System.nanoTime();
		HashMap<String, Author> authors = new HashMap<>();
		
		books.forEach(b -> {
			String authorsNames = b.getAuthors().replace("\"", "");
			String[] splitted = authorsNames.split(",");
			
			for(String s : splitted) {
				s = s.replace("'", "");
				s = s.replace("[", "");
				s = s.replace("]", "");
				
				String name = s.trim();
				
				Author author = authors.get(name);
				if(author != null)
					author.addBook(b);
				else {
					Author ath = new Author(name);
					ath.addBook(b);
					authors.put(name, ath);
				}
			}
		});
		
		long stopTime = System.nanoTime();
		System.out.println("getAuthorsHash Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
		return authors;
	}
	
	public List<Author> getAuthorsList() {
		System.out.println("getAuthors");
		long startTime = System.nanoTime();
		List<Author> authors = new ArrayList<>();
		
		books.forEach(b -> {
			String authorsNames = b.getAuthors().replace("\"", "");
			String[] splitted = authorsNames.split(",");
			
			for(String s : splitted) {
				s = s.replace("'", "");
				s = s.replace("[", "");
				s = s.replace("]", "");
				
				String name = s.trim();
				
				Optional<Author> author = authors.stream().filter(a -> a.getFullName().equals(name)).findFirst();
				if(author.isPresent())
					author.get().addBook(b);
				else {
					Author ath = new Author(name);
					ath.addBook(b);
					authors.add(ath);
				}
			}
		});
		
		long stopTime = System.nanoTime();
		System.out.println("getAuthorsList Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
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
	
	public HashMap<String, Integer> getReviewsForBookWithBook() {
		System.out.println("getReviewsForBook");
		HashMap<String, Integer> map = new HashMap<>();
		reviews.forEach(r -> {
			Optional<Book> book = books.parallelStream().filter(b -> b.getTitle().equals(r.getTitle())).findFirst();
			if(book.isPresent())
				book.get().setNumberOfReviews(book.get().getNumberOfReviews()+1);
			
			Integer value = map.get(r.getTitle());
			if(value == null) 
				map.put(r.getTitle(), 1);
			else {
				Integer val = value+1;
				map.put(r.getTitle(), val);
			}
		});
		
		return map;
	}
	
	public HashMap<String, Integer> getReviewsForBook() {
		System.out.println("getReviewsForBook");
		HashMap<String, Integer> map = new HashMap<>();
		reviews.forEach(r -> {
			
			Integer value = map.get(r.getTitle());
			if(value == null) 
				map.put(r.getTitle(), 1);
			else {
				Integer val = value+1;
				map.put(r.getTitle(), val);
			}
		});
		
		return map;
	}
	
	public Map.Entry<String, Integer> getMostReviewedBook() {
		System.out.println("getMostReviewedBook");
		long startTime = System.nanoTime();
		HashMap<String, Integer> reviewsForBook = getReviewsForBook();

		Map.Entry<String, Integer> value = Collections.max(reviewsForBook.entrySet(), Map.Entry.comparingByValue());
		long stopTime = System.nanoTime();
		System.out.println(value.getKey() + "---" + value.getValue());
		System.out.println("getMostReviewsBook Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
		return value;
	}
	
	/*
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
	}*/
}
