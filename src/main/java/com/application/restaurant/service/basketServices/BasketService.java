package com.application.restaurant.service.basketServices;

import com.application.restaurant.entity.Basket;

public interface BasketService {

    Basket getBasketByRestaurantIdAndUserId(int userId, long restaurantId);

    Basket getBasketById(int id);

    void deleteBasket(int basketId);

    void updateBasket(Basket basket);

    void addBasket(Basket basket);
}
