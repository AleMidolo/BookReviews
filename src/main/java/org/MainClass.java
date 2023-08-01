package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class MainClass {
		
	public static final String COMMA_DELIMITER = ",";
	
	public static Optional<Author> extractMostReviewedAuthor(List<Book> books, List<Review> reviews) {
		ExtractData extractor = new ExtractData(books, reviews);
		List<Author> mostAuthor = extractor.getAuthors();
		Optional<Book> mostBook = extractor.getMostReviewedBook();
		
		if(mostBook.isPresent())
			return mostAuthor.stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
					findFirst();
		else
			return Optional.empty();
	}
	
	public static Optional<Author> extractMostReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<List<Author>> f = CompletableFuture.supplyAsync(() -> extractor.getAuthors());
			Optional<Book> mostBook = extractor.getMostReviewedBook();
			List<Author> mostAuthor = f.get();
			
			if(mostBook.isPresent())
				return mostAuthor.stream().
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
		List<Author> mostAuthor = extractor.getAuthors();
		Optional<Book> mostBook = extractor.getLeastReviewedBook();
		
		if(mostBook.isPresent())
			return mostAuthor.stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
					findFirst();
		else
			return Optional.empty();
	}
	
	public static Optional<Author> extractLeastReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<List<Author>> f = CompletableFuture.supplyAsync(() -> extractor.getAuthors());
			Optional<Book> mostBook = extractor.getLeastReviewedBook();
			List<Author> mostAuthor = f.get();
			
			if(mostBook.isPresent())
				return mostAuthor.stream().
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
		List<Author> mostAuthor = extractor.getAuthors();
		Optional<Book> mostBook = extractor.getAverageReviewedBook();
		
		if(mostBook.isPresent())
			return mostAuthor.stream().
					filter(auth -> auth.getBooks().stream().anyMatch(b -> b.getTitle().equals(mostBook.get().getTitle()))).
					findFirst();
		else
			return Optional.empty();
	}
	
	public static Optional<Author> extractAverageReviewedAuthorParallel(List<Book> books, List<Review> reviews) {
		try {
			ExtractData extractor = new ExtractData(books, reviews);
			CompletableFuture<List<Author>> f = CompletableFuture.supplyAsync(() -> extractor.getAuthors());
			Optional<Book> mostBook = extractor.getAverageReviewedBook();
			List<Author> mostAuthor = f.get();
			
			if(mostBook.isPresent())
				return mostAuthor.stream().
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
}
