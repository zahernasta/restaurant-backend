package com.application.restaurant.dao.foodCategoryDao;

import com.application.restaurant.entity.FoodCategory;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FoodCategoryDAOImpl implements FoodCategoryDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<FoodCategory> getAllCategories() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodCategory> foodCategoryQuery = currentSession.createQuery("from FoodCategory ");

        List<FoodCategory> foodCategoryList = foodCategoryQuery.getResultList();

        return foodCategoryList;
    }

    @Override
    public FoodCategory getCategoryById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodCategory> foodCategoryQuery = currentSession.createQuery("from FoodCategory where id = :id");

        foodCategoryQuery.setParameter("id", id);

        FoodCategory foodCategory = foodCategoryQuery.getSingleResult();

        return foodCategory;
    }

    @Override
    public FoodCategory getCategoryByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodCategory> foodCategoryQuery = currentSession.createQuery("from FoodCategory where name = :name");

        foodCategoryQuery.setParameter("name", name);

        FoodCategory foodCategory = foodCategoryQuery.getSingleResult();

        return foodCategory;
    }

    @Override
    public void saveFoodCategory(FoodCategory foodCategory) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(foodCategory);
    }
}
