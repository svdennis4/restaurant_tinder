package com.techelevator.model;

import java.util.List;

public interface FavouriteDao {
	
	public List<Favourite> getFavouriteList(long userId);
	
	public void addFavourite (long userId, Favourite favourite);
	
	public void setFavouriteList(long userId,List<Favourite> favourites);
	
	public void removeFavourite(long userId,List<Favourite> favourites);

}
