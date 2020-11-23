package com.techelevator.model;

import java.util.List;

public interface PreferencesDao {

	
	public List<Cuisine> getAllCuisines();
	

	public Preferences getPreferences(Long userId);
	

	public void clearPreferences(Long userId);
	
	public void savePreferences(Long userId, Preferences pref);
	


}
