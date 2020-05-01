package com.application.restaurant.service.foodServices;

import com.application.restaurant.dao.foodDao.FoodDAO;
import com.application.restaurant.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    @Override
    public List<Food> getAllFoods() {
        return foodDAO.getAllFoods();
    }

    @Override
    public void saveFood(Food food) {
        foodDAO.saveFood(food);
    }

    @Override
    public Food getFoodById(int id) {
        return foodDAO.getFoodById(id);
    }

    @Override
    public Food getFoodByRestaurantId(int id) {
        return foodDAO.getFoodByRestaurantId(id);
    }

    @Override
    public void updateFood(Food food) {
        foodDAO.updateFood(food);
    }

    @Override
    public void deleteFood(int id) {
        foodDAO.deleteFood(id);
    }
}
