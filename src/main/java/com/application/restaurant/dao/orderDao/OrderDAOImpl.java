package com.application.restaurant.dao.orderDao;

import com.application.restaurant.entity.Order;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Order> getAllOrdersByRestaurantId(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Order> orderQuery = currentSession.createQuery("from Order where restaurant.id = :id");

        orderQuery.setParameter("id", id);

        List<Order> orderList = orderQuery.getResultList();

        return orderList;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Order> orderQuery = currentSession.createQuery("from Order where user.id = :id");

        orderQuery.setParameter("id", id);

        List<Order> orderList = orderQuery.getResultList();

        return orderList;
    }

    @Override
    public Order getOneOrderById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Order> orderQuery = currentSession.createQuery("from Order where id=:id");

        orderQuery.setParameter("id", id);

        Order order = orderQuery.getSingleResult();

        return order;
    }

    @Override
    public void deleteOrder(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Order> orderQuery = currentSession.createQuery("delete from Order where id=:id");

        orderQuery.setParameter("id", id);

        orderQuery.executeUpdate();
    }

    @Override
    public void updateOrder(Order order) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(order);
    }

    @Override
    public void addOrder(Order order) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(order);
    }
}
