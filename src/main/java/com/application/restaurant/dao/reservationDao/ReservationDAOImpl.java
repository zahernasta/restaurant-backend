package com.application.restaurant.dao.reservationDao;

import com.application.restaurant.entity.Reservation;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ReservationDAOImpl implements ReservationDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Reservation getOneReservation(int restaurantId, int userId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Reservation> reservationQuery = currentSession.createQuery("from Reservation where " +
                "user.id = :userId and restaurant.id = :restaurantId order by id DESC ");

        reservationQuery.setParameter("userId", userId);
        reservationQuery.setParameter("restaurantId", restaurantId);

        return reservationQuery.getSingleResult();
    }

    @Override
    public List<Reservation> getAllReservationsByRestaurantId(long restaurantId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Reservation> reservationQuery = currentSession.createQuery("from Reservation where " +
                "restaurant.id = :restaurantId");

        reservationQuery.setParameter("restaurantId", restaurantId);

        return reservationQuery.getResultList();
    }

    @Override
    public List<Reservation> getAllReservationsByUserId(int userId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Reservation> reservationQuery = currentSession.createQuery("from Reservation where " +
                "user.id = :userId");

        reservationQuery.setParameter("userId", userId);

        return reservationQuery.getResultList();
    }

    @Override
    public List<Reservation> getAllReservationByDate(Date date) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Reservation> reservationQuery = currentSession.createQuery("from Reservation where " +
                "date = :date");

        reservationQuery.setParameter("date", date);

        return reservationQuery.getResultList();
    }

    @Override
    public void addOneReservation(Reservation reservation) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.save(reservation);
    }
}
