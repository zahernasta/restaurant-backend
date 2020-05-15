package com.application.restaurant.rest.basket;

import com.application.restaurant.entity.*;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.basketItemServices.BasketItemService;
import com.application.restaurant.service.basketServices.BasketService;
import com.application.restaurant.service.foodServices.FoodService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasketRestController {

    @Autowired
    BasketService basketService;

    @Autowired
    BasketItemService basketItemService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    FoodService foodService;

    @GetMapping("/baskets/users/{user_id}/restaurants/{restaurant_id}")
    public Basket getBasketByUserAndRestaurantId(@PathVariable("user_id") int userId,
                                                 @PathVariable("restaurant_id") long restaurantId)
    {
        Basket basket = basketService.getBasketByRestaurantIdAndUserId(userId, restaurantId);

        return basket;
    }

    @PostMapping("/baskets/users/{user_id}/restaurants/{restaurant_id}")
    public ResponseEntity<String> createNewBasket(@PathVariable("user_id") int userId,
                                                  @PathVariable("restaurant_id") long restaurantId) {

        Basket basket = new Basket();

        User user = userService.getUser(userId);
        if(user == null) {
            throw new NotFoundException("User has not been found");
        }

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        if(restaurant == null) {
            throw new NotFoundException("Restaurant has not been found");
        }

        //TODO: A for each statement to verify if the basket exists or not

        basket.setRestaurant(restaurant);
        basket.setUser(user);

        basketService.addBasket(basket);

        return new ResponseEntity<>("Basket has been created successfully", HttpStatus.OK);
    }

    @PostMapping("/baskets/users/{user_id}/restaurants/{restaurant_id}/basketItems")
    public ResponseEntity<String> addBasketItem(@PathVariable("user_id") int userId,
                                                @PathVariable("restaurant_id") long restaurantId,
                                                @RequestBody BasketItem basketItem) {


        Basket basket = basketService.getBasketByRestaurantIdAndUserId(userId, restaurantId);
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        if(basket == null) {
            User user = userService.getUser(userId);
            if(user == null) {
                throw new NotFoundException("User has not been found");
            }

            if(restaurant == null) {
                throw new NotFoundException("Restaurant has not been found");
            }

            basket = new Basket();
            basket.setUser(user);
            basket.setRestaurant(restaurant);

            basketService.addBasket(basket);
        } else {

            System.out.println(restaurant.getFoodList());
            if(restaurant.getFoodList().isEmpty()) {
                throw new NotFoundException("Food doesn't exist on the restaurant menu");
            } else {
                boolean found = false;
                for(Food food : restaurant.getFoodList()) {
                    if(basketItem.getFood().getId() == food.getId()) {
                        found = true;
                    }
                }

                if(found == false) {
                    throw new NotFoundException("Food doesn't exist on the restaurant menu");
                }
            }

            for(BasketItem bsItem : basket.getBasketItems()) {
                if(basketItem.getFood().getId() == bsItem.getFood().getId()) {
                    BasketItem basketItem1 = basketItemService.getOneBasketItemById(bsItem.getId());
                    basketItem1.setQuantity(basketItem.getQuantity() + bsItem.getQuantity());
                    basketItemService.updateBasketItem(basketItem1);

                    return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
                }
            }

            basketItem.setBasket(basket);
            basketItemService.insertNewBasketItem(basketItem);
        }



        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }

}
