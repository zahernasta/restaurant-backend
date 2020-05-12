package com.application.restaurant.dao.orderItemDao;

import com.application.restaurant.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public OrderItem getOrderItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<OrderItem> orderItemQuery = currentSession.createQuery("from OrderItem where id = :id");

        orderItemQuery.setParameter("id", id);

        OrderItem orderItem = orderItemQuery.getSingleResult();

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(int orderId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<OrderItem> orderItemQuery = currentSession.createQuery("from OrderItem where Order.id=:orderId");

        orderItemQuery.setParameter("orderId", orderId);

        List<OrderItem> orderItemList = orderItemQuery.getResultList();

        return orderItemList;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(orderItem);

    }

    @Override
    public void deleteOrderItem(int orderItemId) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.delete(orderItemId);

    }
}
