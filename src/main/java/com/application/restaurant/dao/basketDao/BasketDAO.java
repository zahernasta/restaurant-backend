package com.application.restaurant.dao.basketDao;

import com.application.restaurant.entity.Basket;

public interface BasketDAO {

    Basket getBasketByRestaurantIdAndUserId(int userId, long restaurantId);

    Basket getBasketById(int id);

    void deleteBasket(int basketId);

    void updateBasket(Basket basket);

    void addBasket(Basket basket);
}
