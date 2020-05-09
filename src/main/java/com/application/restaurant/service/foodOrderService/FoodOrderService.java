package com.application.restaurant.service.foodOrderService;

import com.application.restaurant.entity.FoodOrder;

import java.util.List;

public interface FoodOrderService {

    List<FoodOrder> getAllFoodOrder();

    FoodOrder getOneFoodOrderById(int id);

    List<FoodOrder> getAllFoodOrderByOrderId(int id);

    void deleteOneFoodOrder(int id);

    void updateOneFoodOrder(FoodOrder foodOrder);
}
