package com.application.restaurant.dao.orderDao;

import com.application.restaurant.entity.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> getAllOrdersByRestaurantId(int id);

    List<Order> getAllOrdersByUserId(int id);

    Order getOneOrderById(int id);

    void deleteOrder(int id);

    void updateOrder(Order order);

    void addOrder(Order order);
}
