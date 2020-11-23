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
import com.techelevator.model.Dislike;
import com.techelevator.model.DislikeDao;
import com.techelevator.model.User;

@CrossOrigin
@RestController
public class DislikeController {
	
	@Autowired
    private AuthProvider auth;
	@Autowired
	private DislikeDao dislikeDAO;
	
	@GetMapping(path = "/dislikes")
    public List<Dislike> getDislikes()  {
		// Get user id
    	User currentUser = auth.getCurrentUser();
    	long id = currentUser.getId();
    	
    	// Get dislikes for this user
    	List<Dislike> dislikes= dislikeDAO.getDislikeList(id);
    	
        return dislikes;
	}
	
	@RequestMapping(path="/addDislike", method = RequestMethod.POST)
	public void addDislike(@RequestBody long restaurantId) {
		// Get user id
    	User currentUser = auth.getCurrentUser();
    	long id = currentUser.getId();
    	
    	dislikeDAO.addDislike(id, restaurantId);
	}
	
	@RequestMapping(path="/removeDislike", method = RequestMethod.POST)
	public void removeDislike(@RequestBody long restaurantId) {
		// Get user id
    	User currentUser = auth.getCurrentUser();
    	long id = currentUser.getId();
    	
    	dislikeDAO.removeDislike(id, restaurantId);
	}

}
