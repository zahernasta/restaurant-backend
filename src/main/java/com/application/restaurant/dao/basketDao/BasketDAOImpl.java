package com.application.restaurant.dao.basketDao;

import com.application.restaurant.entity.Basket;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class BasketDAOImpl implements BasketDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Basket getBasketByRestaurantIdAndUserId(int userId, long restaurantId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Basket> basketQuery = currentSession.createQuery("from Basket where " +
                "user.id = :userId and restaurant.id = :restaurantId");

        basketQuery.setParameter("userId", userId);
        basketQuery.setParameter("restaurantId", restaurantId);

        List<Basket> basket = basketQuery.getResultList();

        if(basket.isEmpty()) {
            return null;
        }


        return basket.get(0);
    }

    @Override
    public Basket getBasketById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Basket> basketQuery = currentSession.createQuery("from Basket where id = :id");

        basketQuery.setParameter("id", id);
        List<Basket> basket = basketQuery.getResultList();

        return basket.get(0);
    }

    @Override
    public void deleteBasket(int basketId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Basket> basketQuery = currentSession.createQuery("delete from Basket where id = :id");

        basketQuery.setParameter("id", basketId);

        basketQuery.executeUpdate();
    }

    @Override
    public void updateBasket(Basket basket) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(basket);
    }

    @Override
    public void addBasket(Basket basket) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(basket);
    }
}
