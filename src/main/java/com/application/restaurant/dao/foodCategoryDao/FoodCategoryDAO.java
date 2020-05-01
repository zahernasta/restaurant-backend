package com.application.restaurant.dao.foodCategoryDao;

import com.application.restaurant.entity.FoodCategory;

import java.util.List;

public interface FoodCategoryDAO {

    List<FoodCategory> getAllCategories();

    FoodCategory getCategoryById(int id);

    FoodCategory getCategoryByName(String name);

    void saveFoodCategory(FoodCategory foodCategory);

    void deleteFoodCategory(int id);

}
