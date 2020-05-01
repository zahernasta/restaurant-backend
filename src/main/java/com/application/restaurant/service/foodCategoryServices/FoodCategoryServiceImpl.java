package com.application.restaurant.service.foodCategoryServices;

import com.application.restaurant.dao.foodCategoryDao.FoodCategoryDAO;
import com.application.restaurant.entity.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private FoodCategoryDAO foodCategoryDAO;

    @Override
    public List<FoodCategory> getAllCategories() {
        return foodCategoryDAO.getAllCategories();
    }

    @Override
    public FoodCategory getCategoryById(int id) {
        return foodCategoryDAO.getCategoryById(id);
    }

    @Override
    public FoodCategory getCategoryByName(String name) {
        return foodCategoryDAO.getCategoryByName(name);
    }

    @Override
    public void saveFoodCategory(FoodCategory foodCategory) {
        foodCategoryDAO.saveFoodCategory(foodCategory);
    }

    @Override
    public void deleteFoodCategory(int id) {
        foodCategoryDAO.deleteFoodCategory(id);
    }
}
