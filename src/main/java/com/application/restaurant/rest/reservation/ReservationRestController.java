package com.application.restaurant.rest.reservation;

import com.application.restaurant.entity.Reservation;
import com.application.restaurant.entity.Restaurant;
import com.application.restaurant.entity.User;
import com.application.restaurant.service.reservationService.ReservationService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/reservations/restaurant/{restaurantId}/user/{userId}")
    public ResponseEntity<String> addOneReservation(@PathVariable("restaurantId")int restaurantId,
                                            @PathVariable("userId")int userId,
                                            @RequestBody Reservation reservation) {

        User user = userService.getUser(userId);
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        reservation.setUser(user);
        reservation.setRestaurant(restaurant);

        reservationService.addOneReservation(reservation);

        return new ResponseEntity<>("Reservation added successfully", HttpStatus.OK);
    }

    @GetMapping("/reservations/restaurant/{restaurantId}")
    public List<Reservation> getReservationByRestaurant (@PathVariable("restaurantId")long restaurantId) {

        List<Reservation> reservationList = reservationService.getAllReservationsByRestaurantId(restaurantId);

        return reservationList;
    }

    @GetMapping("/reservations/user/{userId}")
    public List<Reservation> getReservationByUser(@PathVariable("userId") int userId) {

        List<Reservation> reservationList = reservationService.getAllReservationsByUserId(userId);

        return reservationList;
    }
}
