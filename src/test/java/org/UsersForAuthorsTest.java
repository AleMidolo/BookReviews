package org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsersForAuthorsTest {
	
	public static HashMap<String, Book> books = new HashMap<>();
	public static List<Review> reviews = new ArrayList<>();
	public static HashMap<String, Author> authorsSeq;
	public static HashMap<String, Author> authorsPar;
	
	@BeforeClass
	public static void setUp() throws InterruptedException, ExecutionException {
		System.out.println("\nSetUp");
		ExtractDataset ed = new ExtractDataset();
		ed.extractFromDatasetParallel();
		books = ed.getBooks();
		reviews = ed.getReviews();
		authorsSeq = MainClass.getUserForAuthor(books, reviews);
		authorsPar = MainClass.getUserForAuthorParallel(books, reviews);
	}
	
	@Test
	public void authorsSizeTest() {
		System.out.println("\nauthorsSizeTest");
		System.out.println("Number of authors: " + authorsSeq.size() + "---" + authorsPar.size());
		Assert.assertEquals(authorsSeq.size(), authorsPar.size());
	}
	
	@Test
	public void usersSizeTest() {
		System.out.println("\nusersSizeTest");
		Optional<Author> authSeq = authorsSeq.values().stream().
				filter(a -> a.getUsers().size() > 0).
				findFirst();
		
		Author authPar = authorsPar.get(authSeq.get().getFullName());
		
		System.out.println("Number of Users for Author " + authSeq.get().getFullName() + ": " + authSeq.get().getUsers().size() + "---" + authPar.getUsers().size());
		Assert.assertEquals(authSeq.get().getUsers().size(), authPar.getUsers().size());
	}
	
	@Test
	public void getUserTest() {
		System.out.println("\ngetUserTest");
		Optional<Author> authSeq = authorsSeq.values().stream().
				filter(a -> a.getUsers().size() > 0).
				findFirst();
		Author authPar = authorsPar.get(authSeq.get().getFullName());
		
		List<User> usersSeq = new ArrayList<>(authSeq.get().getUsers());
		User userSeq = usersSeq.get(0);
		Optional<User> userPar = authPar.getUsers().stream().
				filter(u -> u.getId().equals(userSeq.getId())).
				findFirst();
		
		Assert.assertTrue(userPar.isPresent());
		if(userPar.isPresent())
			System.out.println("UserID: " + userSeq.getId() + "---" + userPar.get().getId());
	}
	
	@AfterClass
	public static void cleanUp() {
		System.out.println("\ncleanUp");
		books = new HashMap<>();
		reviews = new ArrayList<>();
		authorsSeq = new HashMap<>();
		authorsPar = new HashMap<>();
	}
}
