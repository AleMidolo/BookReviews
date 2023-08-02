# BookReviews
A Java program to extract Book and Reviews data from the Amazon Books Reviews dataset on Kaggle (https://www.kaggle.com/datasets/mohamedbakhet/amazon-books-reviews).
This repository has been used as experiment for the scientific paper: "An Automatic Transformer from Sequential to Parallel Code". In the MainClass file there are
two versions of each method, sequential and parallel. The parallel version is the one created by the approach presented in the paper.

In the /src/main directory there are all the .java files of the application for the extraction of books, reviews and authors from the dataset.
The Package org.benchmark contains all the benchmark used to compare the performances between sequential and parallel version of the refactored methods.
The benchmarks are implemented with the JMH library (https://github.com/openjdk/jmh).

All tests are stored in the /src/test directory, they check whether parallel and sequential version of the same method return equal values.
The output of JaCoCo library(https://www.eclemma.org/jacoco) is saved inside the "target" directory, which shows the code coverage of the test suite.
