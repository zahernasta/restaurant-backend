package com.application.restaurant.service.restaurantServices;

import com.application.restaurant.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getAllRestaurants();

    public void saveRestaurant(Restaurant restaurant);

    public Restaurant getRestaurant(long id);

    public void updateRestaurant(Restaurant restaurant);

    public void deleteRestaurant(long id);

    public List<Restaurant> getAllRestaurantByCuisineId(int cuisineId);

}
