package org;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String userId;
	private String nickName;
	private List<Review> reviews;
	private double mediumScore;
	
	public User(String userId, String nickName) {
		this.userId = userId;
		this.nickName = nickName;
		reviews = new ArrayList<>();
		mediumScore = 0.0;
	}
	
	public String getId() {
		return userId;
	}
	
	public String getNickname() {
		return nickName;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void addReview(Review r) {
		reviews.add(r);
	}
	
	public void updateMediumScore() {
		Double total = reviews.stream().
			map(r -> Double.valueOf(r.getScore())).
			reduce(0.0, (acc, v) -> acc + v);
		
		mediumScore = total / reviews.size();
	}
	
	public Double getMediumScore() {
		return mediumScore;
	}
}
