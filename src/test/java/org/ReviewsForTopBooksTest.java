package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReviewsForTopBooksTest {
	
	public static List<Book> books = new ArrayList<>();
	public static List<Review> reviews = new ArrayList<>();
	public static List<Book> topBooksSeq;
	public static List<Book> topBooksPar;
	public static Book bookSeq = null;
	public static Book bookPar = null;
	/*
	@BeforeClass
	public static void setUp() throws InterruptedException, ExecutionException {
		System.out.println("\nSetUp");
		ExtractDataset ed = new ExtractDataset();
		ed.extractFromDatasetParallel();
		books = ed.getBooks();
		reviews = ed.getReviews();
		topBooksSeq = MainClass.extractReviewsForTopBooks(books, reviews);
		topBooksPar = MainClass.extractReviewsForTopBooksParallel(books, reviews);
	}
	
	@Test
	public void topBooksSizeTest() {
		System.out.println("\ntopBooksSizeTest");
		System.out.println("Top Books sequential: " + topBooksSeq.size() + "Top Books parallel: " + topBooksPar.size());
		Assert.assertEquals(topBooksSeq.size(), topBooksPar.size());
	}
	
	@Test
	public void checkBookPresenceTest() {
		System.out.println("\ncheckBookPresenceTest");
		
		Book b = topBooksSeq.get(0);
		Optional<Book> b2 = topBooksPar.stream().filter(book -> book.equals(b)).findFirst();
		
		Assert.assertTrue(b2.isPresent());
		
		if(b2.isPresent()) {
			bookSeq = b;
			bookPar = b2.get();
		}
	}
	
	@Test
	public void numberOfReviewsForTopBookTest() {
		System.out.println("\nnumberOfReviewsForTopBookTest");
		
		if(bookSeq != null && bookPar != null) {
			System.out.println("Number of reviews sequential: " + bookSeq.getNumberOfReviews() + "Number of reviews parallel: " + bookPar.getNumberOfReviews());
			Assert.assertEquals(bookSeq.getNumberOfReviews(), bookPar.getNumberOfReviews());
		}
		else
			Assert.assertTrue("Books are null", false);
	}
	
	@AfterClass
	public void cleanUp() {
		System.out.println("\ncleanUp");
		books = new ArrayList<>();
		reviews = new ArrayList<>();
	}*/
}
