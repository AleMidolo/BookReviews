package org;

public class Book {

	private String title;
	private String description;
	private String authors;
	private String image;
	private String previewLink;
	private String publisher;
	private String publishedDate;
	private String infoLink;
	private String categories;
	private String ratingCount;
	private Integer numberOfReviews;
	
	public Book(String title, String description, String authors, String image, String previewLink, String publisher, String publishedDate, String infoLink, String categories, String ratingCount) {
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.image = image;
		this.previewLink = previewLink;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.infoLink = infoLink;
		this.categories = categories;
		this.ratingCount = ratingCount;
		numberOfReviews = 0;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getAuthors() {
		return authors;
	}

	public String getImage() {
		return image;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public String getInfoLink() {
		return infoLink;
	}

	public String getCategories() {
		return categories;
	}

	public String getRatingCount() {
		return ratingCount;
	}
	
	public Integer getNumberOfReviews() {
		return numberOfReviews;
		
	}
	
	public void setNumberOfReviews(Integer nor) {
		numberOfReviews = nor;
	}
}
