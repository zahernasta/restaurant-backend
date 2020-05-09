package com.application.restaurant.dao.foodOrderDao;

import com.application.restaurant.entity.FoodOrder;

import java.util.List;

public interface FoodOrderDAO {

    List<FoodOrder> getAllFoodOrder();

    FoodOrder getOneFoodOrderById(int id);

    List<FoodOrder> getAllFoodOrderByOrderId(int id);

    void deleteOneFoodOrder(int id);

    void updateOneFoodOrder(FoodOrder foodOrder);

}
