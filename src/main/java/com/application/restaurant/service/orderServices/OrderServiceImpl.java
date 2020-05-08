package com.application.restaurant.service.orderServices;

import com.application.restaurant.dao.orderDao.OrderDAO;
import com.application.restaurant.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDAO orderDAO;

    @Override
    public List<Order> getAllOrdersByRestaurantId(int id) {
        return orderDAO.getAllOrdersByRestaurantId(id);
    }

    @Override
    public List<Order> getAllOrdersByUserId(int id) {
        return orderDAO.getAllOrdersByUserId(id);
    }

    @Override
    public Order getOneOrderById(int id) {
        return orderDAO.getOneOrderById(id);
    }

    @Override
    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }
}
