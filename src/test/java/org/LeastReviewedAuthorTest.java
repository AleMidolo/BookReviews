package org;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeastReviewedAuthorTest {
	
	public static List<Book> books = new ArrayList<>();
	public static List<Review> reviews = new ArrayList<>();
	public static Optional<Author> authorSeq;
	public static Optional<Author> authorPar;
	/*
	@BeforeClass
	public static void setUp() throws InterruptedException, ExecutionException {
		System.out.println("\nSetUp");
		ExtractDataset ed = new ExtractDataset();
		ed.extractFromDatasetParallel();
		books = ed.getBooks();
		reviews = ed.getReviews();
		authorSeq = MainClass.extractLeastReviewedAuthor(books, reviews);
		authorPar = MainClass.extractLeastReviewedAuthorParallel(books, reviews);
	}
	
	@Test
	public void leastAuthorTest() {
		System.out.println("\nleastAuthorTest");
		if(authorSeq.isPresent() && authorPar.isPresent()) {
			System.out.println("Least Author names: " + authorSeq.get().getFullName() + "---" + authorPar.get().getFullName());
			Assert.assertEquals(authorSeq.get().getFullName(), authorPar.get().getFullName());
		}
		else
			Assert.assertTrue("Authors not present", false);
	}
	
	@Test
	public void booksOfLeastAuthorTest() {
		System.out.println("\nbooksOfLeastAuthorTest");
		
		if(authorSeq.isPresent() && authorPar.isPresent()) {
			System.out.println("Number of Books: " + authorSeq.get().getBooks().size() + "---" + authorPar.get().getBooks().size());
			Assert.assertEquals(authorSeq.get().getBooks().size(), authorPar.get().getBooks().size());
		}
		else
			Assert.assertTrue("Authors not present", false);
	}
	
	@Test
	public void leastValueTest() {
		System.out.println("\nleastValueTest");
		
		if(authorSeq.isPresent() && authorPar.isPresent()) {

			Book bSeq = authorSeq.get().getBooks().get(0);
			Book bPar = authorPar.get().getBooks().get(0);
			
			System.out.println("Least Number of reviews: " + bSeq.getNumberOfReviews() + "---" + bPar.getNumberOfReviews());
			Assert.assertEquals(bSeq.getNumberOfReviews(), bPar.getNumberOfReviews());
		}
		else
			Assert.assertTrue("Authors not present", false);
	}
	
	@AfterClass
	public void cleanUp() {
		System.out.println("\ncleanUp");
		books = new ArrayList<>();
		reviews = new ArrayList<>();
	}*/
}
