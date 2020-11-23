package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Favourite;
import com.techelevator.model.FavouriteDao;
import com.techelevator.model.User;

@CrossOrigin
@RestController

public class FavouriteController {

	@Autowired
	private AuthProvider auth;
	@Autowired
	private FavouriteDao fDAO;

	@GetMapping(path = "/favourites")
	public List<Favourite> getFavouritesPage() {
		// Get user id
		User currentUser = auth.getCurrentUser();
		long id = currentUser.getId();

		// Get restaurant id for this user
		List<Favourite> favourites = new ArrayList<Favourite>();
		favourites = fDAO.getFavouriteList(id);

		return favourites;
	}
	
	@RequestMapping(path = "/addFavourite", method = RequestMethod.POST)
	public void addOneFavourite(@RequestBody long restaurantId) {
		Favourite currentFave = createFavourite(restaurantId);
		
		User currentUser = auth.getCurrentUser();
		long id = currentUser.getId();
		
		fDAO.addFavourite(id, currentFave);
		
	}
	

	@RequestMapping(path = "/favourites", method = RequestMethod.POST)
	public void setFavourites(@RequestBody List<Integer> restaurantIds) {
							  
		User currentUser = auth.getCurrentUser();
		long id = currentUser.getId();

		List<Favourite> favourites = new ArrayList<Favourite>();
		for (Integer i : restaurantIds) {
			favourites.add(createFavourite(i));
		}
		fDAO.setFavouriteList(id, favourites);

	}

	// helper
	private Favourite createFavourite(long restaurantId) {
		Favourite favourite = new Favourite();
		favourite.setRestaurantId(restaurantId);
		return favourite;

	}

}
