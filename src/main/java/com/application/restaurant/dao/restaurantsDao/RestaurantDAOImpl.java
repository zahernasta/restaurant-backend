package com.application.restaurant.dao.restaurantsDao;

import com.application.restaurant.entity.Restaurant;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RestaurantDAOImpl implements RestaurantDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Restaurant> getAllRestaurants() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Restaurant> restaurantQuery =
                currentSession.createQuery("from Restaurant");

        List<Restaurant> restaurants = restaurantQuery.getResultList();

        return restaurants;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(restaurant);
    }

    @Override
    public Restaurant getRestaurant(long id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Restaurant restaurant = currentSession.get(Restaurant.class, id);

        return restaurant;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(restaurant);
    }

    @Override
    public void deleteRestaurant (long id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery("delete from Restaurant  where id  = :userId");

        query.setParameter("userId", id);

        query.executeUpdate();
    }

    @Override
    public List<Restaurant> getAllRestaurantByCuisineId(int cuisineId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Restaurant> query = currentSession.createQuery("from Restaurant where cuisineCategory.id = :id");

        query.setParameter("id", cuisineId);

        return query.getResultList();
    }
}
