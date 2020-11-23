package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;

@Component
public class JdbcPreferencesDao implements PreferencesDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcPreferencesDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public List<Cuisine> getAllCuisines() {
		String sqlSelectAllCuisines = "SELECT * FROM cuisines";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllCuisines);
        List<Cuisine> cuisines = new ArrayList<Cuisine>();
        
        while(results.next()) {
        	cuisines.add(mapResultsToCuisine(results));
        }
       
        return cuisines;
	}

	@Override
	public Preferences getPreferences(Long userId) {
	        String sqlSelectAllPreferences = "SELECT cuisine_id FROM users_cuisines WHERE user_id = ?";
	        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllPreferences, userId);

	        Preferences preferences = mapResultsToPreferences(results);
	       
	        return preferences;
	}
	
	@Override
	public void savePreferences(Long userId, Preferences pref) {
		
		String sqlInsertPreferences = "INSERT INTO users_cuisines (user_id, cuisine_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
		
		for(Integer p : pref.getPreferences()) {
			int result = jdbcTemplate.update(sqlInsertPreferences, userId, p);
		}
	
	}
	
	@Override
	public void clearPreferences(Long userId) {
		String sqlClearPreferences = "DELETE FROM users_cuisines WHERE user_id = ?";
		int result = jdbcTemplate.update(sqlClearPreferences, userId);
	}
	
	
	
	//HELPER METHODS
	private Preferences mapResultsToPreferences(SqlRowSet results) {
		Preferences preferences = new Preferences();
		
		while (results.next()) {
			preferences.addPreference(results.getInt("cuisine_id"));
		}
		
		return preferences;
	    }
	
	private Cuisine mapResultsToCuisine (SqlRowSet results) {
		Cuisine cuisine = new Cuisine();
		
		cuisine.setId(results.getInt("cuisine_id"));
		cuisine.setName(results.getString("cuisine_name"));
		
		return cuisine;
		
	}
	
	

	

	
    
}
