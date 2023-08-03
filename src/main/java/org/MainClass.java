package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class MainClass {
		
	public static final String COMMA_DELIMITER = ",";
	
	public static void main(String[] args) {
		
		ExtractDataset ed = new ExtractDataset();
		ed.extractFromDatasetParallel();
		List<Book> books = ed.getBooks();
		List<Review> reviews = ed.getReviews();
	
		long startTime2 = System.nanoTime();
		MainClass.extractMostReviewedAuthor(books, reviews);
		long stopTime2 = System.nanoTime();
		System.out.println("Total Sequential Time: " + TimeUnit.MILLISECONDS.convert((stopTime2 - startTime2), TimeUnit.NANOSECONDS));
		
		long startTime = System.nanoTime();
		MainClass.extractMostReviewedAuthorParallel(books, reviews);
		long stopTime = System.nanoTime();
		System.out.println("Total Parallel Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
	}
	
	public static Optional<Author> extractMostReviewedAuthor(List<Book> books, List<Review> reviews) {
		ExtractData extractor = new ExtractData(books, reviews);
		
		HashMap<String, Author> mostAuthor = extractor.getAuthorsHash();
		Map.Entry<String, Integer> mostBook = extractor.getMostReviewedBook();
		
		Optional<Author> author = mostAuthor.values().stream().
				filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.getKey()))).
				findFirst();
		
		if(author.isPresent()) {
			Optional<Book> book = author.get().getBooks().stream().filter(b -> b.getTitle().equals(mostBook.getKey())).findFirst();
			book.get().setNumberOfReviews(mostBook.getValue());
		}
		return author;
	}
	
	public static Optional<Author> extractMostReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<HashMap<String, Author>> f = CompletableFuture.supplyAsync(() -> extractor.getAuthorsHash());
			Map.Entry<String, Integer> mostBook = extractor.getMostReviewedBook();
			HashMap<String, Author> mostAuthor = f.get();
			
			Optional<Author> author = mostAuthor.values().stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.getKey()))).
					findFirst();
			
			if(author.isPresent()) {
				Optional<Book> book = author.get().getBooks().stream().filter(b -> b.getTitle().equals(mostBook.getKey())).findFirst();
				book.get().setNumberOfReviews(mostBook.getValue());
			}

			return author;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	/*
	public static List<Book> extractReviewsForTopBooks(List<Book> books, List<Review> reviews) {
		
		ExtractData extractor = new ExtractData(books, reviews);
		List<Book> highestScores = extractor.getBooksWithHighestScore();
		HashMap<Book, Integer> booksAndReviews = extractor.getReviewsForBook();
		
		highestScores.forEach(b -> {
			Integer nor = booksAndReviews.get(b);
			if(nor != null)
				b.setNumberOfReviews(nor);
		});
		
		return highestScores;
	}
	
	public static List<Book> extractReviewsForTopBooksParallel(List<Book> books, List<Review> reviews) {
		
		try{
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<List<Book>> f = CompletableFuture.supplyAsync(() -> extractor.getBooksWithHighestScore());
			HashMap<Book, Integer> booksAndReviews = extractor.getReviewsForBook();
			
			List<Book> highestScores = f.get();
			
			highestScores.forEach(b -> {
				Integer nor = booksAndReviews.get(b);
				if(nor != null)
					b.setNumberOfReviews(nor);
			});

			return highestScores;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static Optional<Author> extractLeastReviewedAuthor(List<Book> books, List<Review> reviews) {
		ExtractData extractor = new ExtractData(books, reviews);
		HashMap<String, Author> mostAuthor = extractor.getAuthors();
		Optional<Book> mostBook = extractor.getLeastReviewedBook();
		
		if(mostBook.isPresent())
			return mostAuthor.values().stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
					findFirst();
		else
			return Optional.empty();
	}
	
	public static Optional<Author> extractLeastReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<HashMap<String, Author> > f = CompletableFuture.supplyAsync(() -> extractor.getAuthors());
			Optional<Book> mostBook = extractor.getLeastReviewedBook();
			HashMap<String, Author>  mostAuthor = f.get();
			
			if(mostBook.isPresent())
				return mostAuthor.values().stream().
						filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
						findFirst();
			else
				return Optional.empty();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	public static Optional<Author> extractAverageReviewedAuthor(List<Book> books, List<Review> reviews) {
		ExtractData extractor = new ExtractData(books, reviews);
		HashMap<String, Author>  mostAuthor = extractor.getAuthors();
		Optional<Book> mostBook = extractor.getAverageReviewedBook();
		
		if(mostBook.isPresent())
			return mostAuthor.values().stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
					findFirst();
		else
			return Optional.empty();
	}
	
	public static Optional<Author> extractAverageReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<HashMap<String, Author> > f = CompletableFuture.supplyAsync(() -> extractor.getAuthors());
			Optional<Book> mostBook = extractor.getAverageReviewedBook();
			HashMap<String, Author>  mostAuthor = f.get();
			
			if(mostBook.isPresent())
				return mostAuthor.values().stream().
						filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
						findFirst();
			else
				return Optional.empty();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}*/
}
