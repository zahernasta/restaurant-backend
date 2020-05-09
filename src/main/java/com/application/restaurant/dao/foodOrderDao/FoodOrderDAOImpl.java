package com.application.restaurant.dao.foodOrderDao;

import com.application.restaurant.entity.FoodOrder;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FoodOrderDAOImpl implements FoodOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<FoodOrder> getAllFoodOrder() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodOrder> foodOrderQuery = currentSession.createQuery(" from FoodOrder ");

        List<FoodOrder> foodOrderList = foodOrderQuery.getResultList();

        return foodOrderList;
    }

    @Override
    public FoodOrder getOneFoodOrderById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodOrder> foodOrderQuery = currentSession.createQuery(" from FoodOrder where id=:id");

        foodOrderQuery.setParameter("id", id);

        FoodOrder foodOrder = foodOrderQuery.getSingleResult();

        return foodOrder;
    }

    @Override
    public List<FoodOrder> getAllFoodOrderByOrderId(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodOrder> foodOrderQuery = currentSession.createQuery("from FoodOrder where order.id=:id");

        foodOrderQuery.setParameter("id", id);

        List<FoodOrder> foodOrderList = foodOrderQuery.getResultList();

        return foodOrderList;
    }

    @Override
    public void deleteOneFoodOrder(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<FoodOrder> foodOrderQuery = currentSession.createQuery("delete from FoodOrder where id=:id");

        foodOrderQuery.setParameter("id", id);

        foodOrderQuery.executeUpdate();
    }

    @Override
    public void updateOneFoodOrder(FoodOrder foodOrder) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.update(foodOrder);
    }
}
