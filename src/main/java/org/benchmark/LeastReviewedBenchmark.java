package org.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.Book;
import org.ExtractDataset;
import org.MainClass;
import org.Review;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

public class LeastReviewedBenchmark {
	
	@State(Scope.Benchmark)
    public static class MyState {

		List<Book> books = new ArrayList<>();
		List<Review> reviews = new ArrayList<>();

		@Setup(Level.Trial)
		public void setUp() {
			System.out.println("\nSetUp");
			ExtractDataset ed = new ExtractDataset();
			ed.extractFromDatasetParallel();
			books = ed.getBooks();
			reviews = ed.getReviews();
		}
    }

	@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
	@Fork(value=1, warmups=1)
	@OutputTimeUnit(TimeUnit.SECONDS)
	@Benchmark
    public static void leastReviewedAuthorSequential(MyState myState) {
		long startTime = System.nanoTime();
		MainClass.extractLeastReviewedAuthor(myState.books, myState.reviews);
		long stopTime = System.nanoTime();
		System.out.println("Total Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
	}
	
	@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
	@Fork(value=1, warmups=1)
	@OutputTimeUnit(TimeUnit.SECONDS)
	@Benchmark
    public static void leastReviewedAuthorParallel(MyState myState) {
		long startTime = System.nanoTime();
		MainClass.extractLeastReviewedAuthorParallel(myState.books, myState.reviews);
		long stopTime = System.nanoTime();
		System.out.println("Total Time: " + TimeUnit.MILLISECONDS.convert((stopTime - startTime), TimeUnit.NANOSECONDS));
	}
}
