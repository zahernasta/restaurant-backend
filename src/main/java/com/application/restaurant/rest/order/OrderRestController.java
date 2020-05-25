package com.application.restaurant.rest.order;

import com.application.restaurant.entity.*;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.basketItemServices.BasketItemService;
import com.application.restaurant.service.basketServices.BasketService;
import com.application.restaurant.service.foodServices.FoodService;
import com.application.restaurant.service.orderItemServices.OrderItemService;
import com.application.restaurant.service.orderServices.OrderService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    BasketService basketService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    BasketItemService basketItemService;

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

    @PostMapping("/orders/users/{user_id}/restaurants/{restaurant_id}")
    public ResponseEntity<String> insertNewOrder(@PathVariable("user_id") int userId,
                                @PathVariable("restaurant_id") int restaurantId,
                                @RequestBody Order order) {

        Basket basket = basketService.getBasketByRestaurantIdAndUserId(userId, restaurantId);
        User user = userService.getUser(userId);
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        order.setBasket(basket);
        order.setUser(user);
        order.setRestaurant(restaurant);

        orderService.addOrder(order);

        for(BasketItem basketItem : basket.getBasketItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(0);
            orderItem.setFood(basketItem.getFood());
            orderItem.setQuantity(basketItem.getQuantity());
            orderItem.setOrder(order);
            System.out.println(basketItem.getId());
            orderItemService.addOrderItem(orderItem);
            basketItemService.deleteBasketItemById(basketItem.getId());
        }


        return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
    }

}
