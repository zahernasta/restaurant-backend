package com.application.restaurant.service.reservationService;

import com.application.restaurant.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    public Reservation getOneReservation(int restaurantId, int userId);

    public List<Reservation> getAllReservationsByRestaurantId(long restaurantId);

    public List<Reservation> getAllReservationsByUserId(int userId);

    public List<Reservation> getAllReservationByDate(Date date);

    public void addOneReservation(Reservation reservation);
}
