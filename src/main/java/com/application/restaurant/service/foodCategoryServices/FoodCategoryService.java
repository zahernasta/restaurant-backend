package com.application.restaurant.service.foodCategoryServices;

import com.application.restaurant.entity.FoodCategory;

import java.util.List;

public interface FoodCategoryService {
    List<FoodCategory> getAllCategories();

    FoodCategory getCategoryById(int id);

    FoodCategory getCategoryByName(String name);

    void saveFoodCategory(FoodCategory foodCategory);
}
