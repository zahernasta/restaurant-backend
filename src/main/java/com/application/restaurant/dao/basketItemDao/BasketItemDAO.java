package com.application.restaurant.dao.basketItemDao;

import com.application.restaurant.entity.BasketItem;

import java.util.List;

public interface BasketItemDAO {

    List<BasketItem> getAllBasketItems();

    List<BasketItem> getBasketItemsByUserId(int id);

    BasketItem getOneBasketItemById(int id);

    void deleteBasketItemById(int id);

    void updateBasketItem(int id, int quantity);

    void insertNewBasketItem(BasketItem basketItem);
}
