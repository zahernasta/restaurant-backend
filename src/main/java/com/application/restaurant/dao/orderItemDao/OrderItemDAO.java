package com.application.restaurant.dao.orderItemDao;

import com.application.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemDAO {

    OrderItem getOrderItemById(int id);

    List<OrderItem> getOrderItemByOrderId(int orderId);

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(int orderItemId);

}
