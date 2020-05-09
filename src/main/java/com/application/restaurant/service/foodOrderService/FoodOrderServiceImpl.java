package com.application.restaurant.service.foodOrderService;

import com.application.restaurant.dao.foodOrderDao.FoodOrderDAO;
import com.application.restaurant.entity.FoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {

    @Autowired
    FoodOrderDAO foodOrderDAO;

    @Override
    public List<FoodOrder> getAllFoodOrder() {
        return foodOrderDAO.getAllFoodOrder();
    }

    @Override
    public FoodOrder getOneFoodOrderById(int id) {
        return foodOrderDAO.getOneFoodOrderById(id);
    }

    @Override
    public List<FoodOrder> getAllFoodOrderByOrderId(int id) {
        return foodOrderDAO.getAllFoodOrderByOrderId(id);
    }

    @Override
    public void deleteOneFoodOrder(int id) {
        foodOrderDAO.deleteOneFoodOrder(id);
    }

    @Override
    public void updateOneFoodOrder(FoodOrder foodOrder) {
        foodOrderDAO.updateOneFoodOrder(foodOrder);
    }
}
