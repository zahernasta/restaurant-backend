package com.application.restaurant.service.foodServices;

import com.application.restaurant.entity.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAllFoods();

    void saveFood(Food food);

    Food getFoodById(int id);

    Food getFoodByRestaurantId(int id);

    void deleteFood(int id);
}
