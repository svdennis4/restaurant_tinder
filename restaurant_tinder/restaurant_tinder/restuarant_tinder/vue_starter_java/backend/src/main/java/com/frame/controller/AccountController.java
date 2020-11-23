package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.Preferences;
import com.techelevator.model.PreferencesDao;
import com.techelevator.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AccountController
 */
@CrossOrigin 
@RestController
public class AccountController {
    @Autowired
    private AuthProvider auth;
    
    @Autowired
    private PreferencesDao pDAO;

    @Autowired
    private JwtTokenHandler tokenHandler;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, RedirectAttributes flash) throws UnauthorizedException {
        if (auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(user.getUsername(), currentUser.getRole());
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
        if (result.hasErrors()) {
            String errorMessages = "";
            for (ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
        auth.register(user.getUsername(),user.getEmail() ,user.getPassword(), user.getRole());
        return "{\"success\":true}";
    }
    
//    @CrossOrigin
//    @GetMapping(path = "/preferences")
//    public Preferences getPreferencePage()  {
//    	// Get user id
//    	User currentUser = auth.getCurrentUser();
//    	long id = currentUser.getId();
//    	
//    	// Get preferences for this user
//    	Preferences userPreferences = pDAO.getPreferences(id);
//    	
//        return userPreferences;
//    }
//    
//    
//    @RequestMapping(path = "/preferences", method = RequestMethod.POST)
//    public void setPreferences(@RequestBody List<Integer> p)  {
//    	System.out.println("Request Mapping reached.");
//    	
//    	User currentUser = auth.getCurrentUser();
//    	long id = currentUser.getId();
//    	
//    	Preferences preferences = new Preferences();
//    	preferences.setAllPreferences(p);
//    	pDAO.savePreferences(id, preferences);
//    	
//    }
    

    
}

