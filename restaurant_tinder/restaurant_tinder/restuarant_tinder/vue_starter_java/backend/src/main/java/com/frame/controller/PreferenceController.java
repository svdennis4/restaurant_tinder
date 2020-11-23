package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Cuisine;
import com.techelevator.model.Preferences;
import com.techelevator.model.PreferencesDao;
import com.techelevator.model.User;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PreferenceController {
		
		@Autowired
	    private AuthProvider auth;
		@Autowired
		private PreferencesDao pDAO;
		
		@GetMapping(path = "/cuisines")
	    public List<Cuisine> getCuisines()  {
	    	List<Cuisine> allCuisines = pDAO.getAllCuisines();
	    	
	        return allCuisines;
	    }
		
		@GetMapping(path = "/preferences")
	    public Preferences getPreferencePage()  {
	    	// Get user id
	    	User currentUser = auth.getCurrentUser();
	    	long id = currentUser.getId();
	    	
	    	// Get preferences for this user
	    	Preferences userPreferences = pDAO.getPreferences(id);
	    	
	        return userPreferences;
	    }
	    
	    
	    @RequestMapping(path = "/preferences", method = RequestMethod.POST)
	    public void setPreferences(@RequestBody List<Integer> p)  {
	    	
	    	User currentUser = auth.getCurrentUser();
	    	long id = currentUser.getId();
	    	
	    	Preferences preferences = new Preferences();
	    	preferences.setPreferences(p);
	    	pDAO.clearPreferences(id);
	    	pDAO.savePreferences(id, preferences);
	    	
	    }
		
	

}
