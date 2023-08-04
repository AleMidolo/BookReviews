package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtractDataTest {
	
	public static HashMap<String, Book> booksSeq;
	public static HashMap<String, Book> booksPar;
	public static List<Review> reviewsSeq;
	public static List<Review> reviewsPar;
	
	
	@BeforeClass
	public static void setUp() {
		ExtractDataset ed = new ExtractDataset();
		ed.extractFromDatasetParallel();
		booksPar = ed.getBooks();
		reviewsPar = ed.getReviews();
		ed = new ExtractDataset();
		ed.extractFromDataset();
		booksSeq = ed.getBooks();
		reviewsSeq = ed.getReviews();
	}
	
	@Test
	public void extractBooksTest() {
		System.out.println("\nextractBooksTest");
		System.out.println("Books extracted sequential: " + booksSeq.size() + "\nBooks extracted parallel: " + booksPar.size());
		Assert.assertEquals(booksSeq.size(), booksPar.size());
	}
	
	@Test
	public void extractReviewsTest() {
		System.out.println("\nextractBooksAndReviewsTest2");
		System.out.println("Reviews extracted sequential: " + reviewsSeq.size() + "\nReviews extracted parallel: " + reviewsPar.size());
		Assert.assertEquals(reviewsSeq.size(), reviewsPar.size());
	}
	
	@AfterClass
	public static void cleanUp() {
		System.out.println("\ncleanUp");
		booksSeq = new HashMap<>();
		reviewsSeq = new ArrayList<>();
		booksPar = new HashMap<>();
		reviewsPar = new ArrayList<>();
	}
}
