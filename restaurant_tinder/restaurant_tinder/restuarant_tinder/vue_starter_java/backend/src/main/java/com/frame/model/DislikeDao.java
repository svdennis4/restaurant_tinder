package com.techelevator.model;

import java.util.List;

public interface DislikeDao {
	
	public List<Dislike> getDislikeList(long userId);
	
	public void addDislike(long userId, long restaurantId);
	
	public void removeDislike(long userId, long restaurantId);
}
