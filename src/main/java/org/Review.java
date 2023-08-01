package org;

public class Review {
	
	public String id;
	public String title;
	public String price;
	public String userID;
	public String profileName;
	public String helpfulness;
	public String score;
	public String time;
	public String summary;
	public String text;
	
	public Review(String id, String title, String price, String userID, String profileName, String helpfulness, String score, String time, String summary, String text) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.userID = userID;
		this.profileName = profileName;
		this.helpfulness = helpfulness;
		this.score = score;
		this.time = time;
		this.summary = summary;
		this.text = text;
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getPrice() {
		return price;
	}
	public String getUserID() {
		return userID;
	}
	public String getProfileName() {
		return profileName;
	}
	public String getHelpfulness() {
		return helpfulness;
	}
	public String getScore() {
		return score;
	}
	public String getTime() {
		return time;
	}
	public String getSummary() {
		return summary;
	}
	public String getText() {
		return text;
	}
}
