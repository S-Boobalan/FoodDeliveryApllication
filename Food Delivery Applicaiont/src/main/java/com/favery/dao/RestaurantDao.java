package com.favery.dao;

import java.util.List;
import java.util.Map;

import com.favery.model.Restaurant;

public interface RestaurantDao {
    
    int addRestaurant(Restaurant restaurant);
    
    Restaurant getRestaurant(int restaurantId);

    int updateRestaurant(Restaurant restaurant);

    int deleteRestaurant(int restaurantId); 

    List<Restaurant> getAllRestaurants();
}
