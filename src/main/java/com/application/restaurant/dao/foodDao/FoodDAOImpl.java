package com.application.restaurant.dao.foodDao;

import com.application.restaurant.entity.Food;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FoodDAOImpl implements FoodDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Food> getAllFoods() {
        Session session = sessionFactory.getCurrentSession();

        Query<Food> foodQuery = session.createQuery("from Food");

        List<Food> foodList = foodQuery.getResultList();

        return foodList;

    }

    @Override
    public void saveFood(Food food) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(food);
    }

    @Override
    public Food getFoodById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Food> foodQuery = session.createQuery("from Food where id = :id");

        foodQuery.setParameter("id", id);

        Food food = foodQuery.getSingleResult();

        return food;
    }

    @Override
    public Food getFoodByRestaurantId(int id) {
        return null;
    }

    @Override
    public void updateFood(Food food) {
        Session session = sessionFactory.getCurrentSession();

        session.update(food);
    }

    @Override
    public void deleteFood(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Food> foodQuery = session.createQuery("delete from Food where id = :id");

        foodQuery.setParameter("id", id);

        foodQuery.executeUpdate();
    }
}
