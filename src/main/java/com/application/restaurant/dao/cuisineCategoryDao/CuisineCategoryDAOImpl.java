package com.application.restaurant.dao.cuisineCategoryDao;

import com.application.restaurant.entity.CuisineCategory;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CuisineCategoryDAOImpl implements CuisineCategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<CuisineCategory> getAllCuisineCategories() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CuisineCategory> cuisineCategoryQuery = currentSession.createQuery("from CuisineCategory ");

        List<CuisineCategory> cuisineCategories = cuisineCategoryQuery.getResultList();

        return cuisineCategories;
    }

    @Override
    public CuisineCategory getCuisineById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CuisineCategory> cuisineCategoryQuery = currentSession.createQuery(" from CuisineCategory where " +
                "id = :id");

        cuisineCategoryQuery.setParameter("id", id);

        return cuisineCategoryQuery.getSingleResult();
    }

    @Override
    public CuisineCategory getCuisineByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CuisineCategory> cuisineCategoryQuery = currentSession.createQuery(" from CuisineCategory where " +
                "name = :name");

        cuisineCategoryQuery.setParameter("name", name);

        return cuisineCategoryQuery.getSingleResult();
    }

    @Override
    public void addCuisine(CuisineCategory cuisineCategory) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(cuisineCategory);
    }

    @Override
    public void deleteCuisine(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<CuisineCategory> cuisineCategoryQuery =
                currentSession.createQuery("delete from CuisineCategory where id = :id");

        cuisineCategoryQuery.setParameter("id", id);

        cuisineCategoryQuery.executeUpdate();

    }
}
