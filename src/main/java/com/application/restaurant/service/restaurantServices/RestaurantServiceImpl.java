package com.application.restaurant.service.restaurantServices;

import com.application.restaurant.dao.restaurantsDao.RestaurantDAO;
import com.application.restaurant.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantDAO.getAllRestaurants();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantDAO.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurant(long id) {
        return restaurantDAO.getRestaurant(id);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        restaurantDAO.updateRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(long id) {
        restaurantDAO.deleteRestaurant(id);
    }
}
