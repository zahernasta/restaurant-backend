package com.application.restaurant.rest.order;

import com.application.restaurant.entity.Order;
import com.application.restaurant.service.foodServices.FoodService;
import com.application.restaurant.service.orderServices.OrderService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    FoodService foodService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/orders/restaurant/{restaurant_id}")
    public List<Order> getAllOrdersByRestaurantId(@PathVariable("restaurant_id") int id) {
        return orderService.getAllOrdersByRestaurantId(id);
    }

    @GetMapping("/orders/user/{user_id}")
    public List<Order> getAllOrdersByUserId(@PathVariable("user_id") int id) {
        return orderService.getAllOrdersByUserId(id);
    }

    @GetMapping("/orders/{order_id}")
    public Order getOneOrderById(@PathVariable("order_id")int id) {
        return orderService.getOneOrderById(id);
    }

}
