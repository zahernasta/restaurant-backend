package com.application.restaurant.service.orderItemServices;

import com.application.restaurant.dao.orderItemDao.OrderItemDAO;
import com.application.restaurant.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemDAO orderItemDAO;

    @Override
    public OrderItem getOrderItemById(int id) {
        return orderItemDAO.getOrderItemById(id);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(int orderId) {
        return orderItemDAO.getOrderItemByOrderId(orderId);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemDAO.addOrderItem(orderItem);
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        orderItemDAO.deleteOrderItem(orderItemId);
    }
}
