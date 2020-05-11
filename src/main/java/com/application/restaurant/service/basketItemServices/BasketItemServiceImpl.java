package com.application.restaurant.service.basketItemServices;

import com.application.restaurant.dao.basketItemDao.BasketItemDAO;
import com.application.restaurant.entity.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketItemServiceImpl implements BasketItemService {

    @Autowired
    BasketItemDAO basketItemDAO;

    @Override
    public List<BasketItem> getAllBasketItems() {
        return basketItemDAO.getAllBasketItems();
    }

    @Override
    public List<BasketItem> getBasketItemsByUserId(int id) {
        return basketItemDAO.getBasketItemsByUserId(id);
    }

    @Override
    public BasketItem getOneBasketItemById(int id) {
        return basketItemDAO.getOneBasketItemById(id);
    }

    @Override
    public void deleteBasketItemById(int id) {
        basketItemDAO.deleteBasketItemById(id);
    }

    @Override
    public void updateBasketItem(BasketItem basketItem) {
        basketItemDAO.updateBasketItem(basketItem);
    }

    @Override
    public void insertNewBasketItem(BasketItem basketItem) {
        basketItemDAO.insertNewBasketItem(basketItem);
    }
}
