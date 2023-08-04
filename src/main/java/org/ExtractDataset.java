package org;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ExtractDataset {
	
	private HashMap<String, Book> books;
	private List<Review> reviews;
	
	public ExtractDataset() {
		books = new HashMap<>();
		reviews = new ArrayList<>();
	}

	private List<Review> extractReviews() {
		System.out.println("Extract Reviews...");
		List<Review> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("books_rating.csv"))) {
		    String line = br.readLine();
		    while ((line = br.readLine()) != null) {
		    	 String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		    	 String title = tokens[1].replace("\"", "");
		    	 Review review = new Review(tokens[0] , title, tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9]);
		         list.add(review);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private HashMap<String, Book> extractBooks(){
		System.out.println("Extract Books...");
		HashMap<String, Book> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("books_data.csv"))) {
		    String line = br.readLine();
		    while ((line = br.readLine()) != null) {
		    	 String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		    	 Book newBook = new Book(tokens[0] , tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8], tokens[9]);
		         map.put(newBook.getTitle(), newBook);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public void extractFromDataset() {
		books = extractBooks();
		reviews = extractReviews();
	}
	
	public void extractFromDatasetParallel() {
		try {
			CompletableFuture<HashMap<String, Book>> future = CompletableFuture.supplyAsync(() -> extractBooks());
			reviews = extractReviews();
			books = future.get();
			System.out.println("Books: " + books.size() + "\nReviews: " + reviews.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Book> getBooks() {
		return books;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
}
