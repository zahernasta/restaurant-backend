package com.application.restaurant.service.orderItemServices;

import com.application.restaurant.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem getOrderItemById(int id);

    List<OrderItem> getOrderItemByOrderId(int orderId);

    void addOrderItem(OrderItem orderItem);

    void deleteOrderItem(int orderItemId);

}
