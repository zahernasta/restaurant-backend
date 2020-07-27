package com.application.restaurant.service.reservationService;

import com.application.restaurant.dao.reservationDao.ReservationDAO;
import com.application.restaurant.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    public Reservation getOneReservation(int restaurantId, int userId) {
        return reservationDAO.getOneReservation(restaurantId, userId);
    }

    @Override
    public List<Reservation> getAllReservationsByRestaurantId(long restaurantId) {
        return reservationDAO.getAllReservationsByRestaurantId(restaurantId);
    }

    @Override
    public List<Reservation> getAllReservationsByUserId(int userId) {
        return reservationDAO.getAllReservationsByUserId(userId);
    }

    @Override
    public List<Reservation> getAllReservationByDate(Date date) {
        return reservationDAO.getAllReservationByDate(date);
    }

    @Override
    public void addOneReservation(Reservation reservation) {
        reservationDAO.addOneReservation(reservation);
    }
}
