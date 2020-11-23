package com.techelevator.model;

import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component

public class JdbcFavouriteDao implements FavouriteDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcFavouriteDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Favourite> getFavouriteList(long userId) {
		String sqlGetAllFavs = "SELECT restaurant_id FROM favourites WHERE user_id =?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllFavs, userId);

		List<Favourite> favourites = new ArrayList<Favourite>();
		while (results.next()) {
			favourites.add(mapResultsToFavourite(results));
		}

		return favourites;
	}
	
	@Override
	public void addFavourite (long userId, Favourite favourite) {
		String sqlSetOneFav = "INSERT INTO favourites (user_id, restaurant_id) VALUES (?,?)  ON CONFLICT DO NOTHING";
		jdbcTemplate.update(sqlSetOneFav, userId, favourite.getRestaurantId());
	}

	@Override
	public void setFavouriteList(long userId, List<Favourite> favourites) {
		String sqlSetFav = "INSERT INTO favourites (user_id,restaurant_id) VALUES (?,?)  ON CONFLICT DO NOTHING";
		for (Favourite f : favourites) {
			jdbcTemplate.update(sqlSetFav, userId, f.getRestaurantId());
		}
	}

	@Override
	public void removeFavourite(long userId, List<Favourite> favourites) {
		String sqlDeleteFav = "DELETE FROM favourites WHERE user_id =? AND restaurant_id = ?";
		for (Favourite f : favourites) {
			jdbcTemplate.update(sqlDeleteFav, userId, f.getRestaurantId());
		}
		
	

	}

	private Favourite mapResultsToFavourite(SqlRowSet results) {

		Favourite newFavourite = new Favourite();

		newFavourite.setRestaurantId(results.getLong("restaurant_id"));

		return newFavourite;
	}

}
