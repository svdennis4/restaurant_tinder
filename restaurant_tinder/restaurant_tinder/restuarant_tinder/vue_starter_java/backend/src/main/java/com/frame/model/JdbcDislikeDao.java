package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class JdbcDislikeDao implements DislikeDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcDislikeDao (DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public List<Dislike> getDislikeList(long userId) {
		String sqlSelectAllDislikes = "SELECT * FROM dislikes WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllDislikes, userId);
        List<Dislike> dislikes = new ArrayList<Dislike>();
        
        while(results.next()) {
        	dislikes.add(mapRowToDislike(results));
        }
       
        return dislikes;
	}
	
	public void addDislike(long userId, long restaurantId) {
		String sqlInsertDislike = "INSERT INTO dislikes (user_id, restaurant_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
		int result = jdbcTemplate.update(sqlInsertDislike, userId, restaurantId);

	}
	
	public void removeDislike(long userId, long restaurantId) {
		String sqlRemoveDislike = "DELETE FROM dislikes WHERE user_id = ? AND restaurant_id = ?)";
		int result = jdbcTemplate.update(sqlRemoveDislike, userId, restaurantId);
	}
	
	
	//HELPER METHODS
	private Dislike mapRowToDislike(SqlRowSet results) {
		Dislike dislike = new Dislike();
		
		dislike.setRestaurantId(results.getInt("restaurant_id"));
		
		return dislike;
		
	}
	

}
