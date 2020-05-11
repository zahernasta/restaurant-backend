package com.application.restaurant.service.basketItemServices;

import com.application.restaurant.entity.BasketItem;

import java.util.List;

public interface BasketItemService {

    List<BasketItem> getAllBasketItems();

    List<BasketItem> getBasketItemsByUserId(int id);

    BasketItem getOneBasketItemById(int id);

    void deleteBasketItemById(int id);

    void updateBasketItem(BasketItem basketItem);

    void insertNewBasketItem(BasketItem basketItem);

}
