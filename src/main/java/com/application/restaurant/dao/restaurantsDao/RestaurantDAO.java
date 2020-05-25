package com.application.restaurant.dao.restaurantsDao;

import com.application.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantDAO {

    public List<Restaurant> getAllRestaurants();

    public void saveRestaurant(Restaurant restaurant);

    public Restaurant getRestaurant(long id);

    public void updateRestaurant(Restaurant restaurant);

    public void deleteRestaurant(long id);

    public List<Restaurant> getAllRestaurantByCuisineId(int cuisineId);
}
