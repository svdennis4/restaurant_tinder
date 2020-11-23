package com.techelevator.model;

import java.util.List;
import java.util.ArrayList;

public class Preferences {
	
	private List<Integer> preferences = new ArrayList<>();
	
	
	
	public List<Integer> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Integer> preferences) {
		this.preferences = preferences;
	}
	
	public void addPreference(int cuisineId) {
		this.preferences.add(cuisineId);
	}

	
	
}
