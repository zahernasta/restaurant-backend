package com.application.restaurant.service.basketServices;

import com.application.restaurant.dao.basketDao.BasketDAO;
import com.application.restaurant.entity.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketDAO basketDAO;

    @Override
    public Basket getBasketByRestaurantIdAndUserId(int userId, long restaurantId) {
        return basketDAO.getBasketByRestaurantIdAndUserId(userId, restaurantId);
    }

    @Override
    public Basket getBasketById(int id) {
        return basketDAO.getBasketById(id);
    }

    @Override
    public void deleteBasket(int basketId) {
        basketDAO.deleteBasket(basketId);
    }

    @Override
    public void updateBasket(Basket basket) {
        basketDAO.updateBasket(basket);
    }

    @Override
    public void addBasket(Basket basket) {
        basketDAO.addBasket(basket);
    }
}
