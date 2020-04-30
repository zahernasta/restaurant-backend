package com.application.restaurant.dao.foodDao;

import com.application.restaurant.entity.Food;

import java.util.List;

public interface FoodDAO {

    List<Food> getAllFoods();

    void saveFood(Food food);

    Food getFoodById(int id);

    Food getFoodByRestaurantId(int id);

    void deleteFood(int id);
}
