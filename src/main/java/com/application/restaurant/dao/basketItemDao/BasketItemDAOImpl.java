package com.application.restaurant.dao.basketItemDao;

import com.application.restaurant.entity.BasketItem;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class BasketItemDAOImpl implements BasketItemDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<BasketItem> getAllBasketItems() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<BasketItem> basketItemQuery = currentSession.createQuery(" from BasketItem ");

        List<BasketItem> basketItemList = basketItemQuery.getResultList();

        return basketItemList;
    }

    @Override
    public List<BasketItem> getBasketItemsByUserId(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<BasketItem> basketItemQuery = currentSession.createQuery("from BasketItem where user.id = :id");

        basketItemQuery.setParameter("id", id);

        List<BasketItem> basketItemList = basketItemQuery.getResultList();

        return basketItemList;
    }

    @Override
    public BasketItem getOneBasketItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<BasketItem> basketItemQuery = currentSession.createQuery("from BasketItem where id = :id");

        basketItemQuery.setParameter("id", id);

        BasketItem basketItem = basketItemQuery.getSingleResult();

        return basketItem;
    }

    @Override
    public void deleteBasketItemById(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<BasketItem> basketItemQuery = currentSession.createQuery("delete from BasketItem where id = :id");

        basketItemQuery.setParameter("id", id);

        basketItemQuery.executeUpdate();
    }

    @Override
    public void updateBasketItem(int id, int quantity) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<BasketItem> basketItemQuery = currentSession.createQuery("update BasketItem set quantity = :quantity " +
                "where id = :id ");

        basketItemQuery.setParameter("quantity", quantity);
        basketItemQuery.setParameter("id", id);

        basketItemQuery.executeUpdate();

    }

    @Override
    public void insertNewBasketItem(BasketItem basketItem) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(basketItem);

    }
}
