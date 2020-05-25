package com.application.restaurant.rest.user;

import com.application.restaurant.entity.Restaurant;
import com.application.restaurant.entity.User;
import com.application.restaurant.rest.exceptions.EmptyFieldsException;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/users")
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.getUser(id);

        if(user == null) {
            throw new NotFoundException("User id not found " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public User addCustomer(@RequestBody User user) {

        if(user.getUsername() == null) {
            throw new EmptyFieldsException("Username field is empty");
        }

         if(user.getEmail() == null) {
            throw new EmptyFieldsException("Email field is empty");
        }

        user.setId(0);

        userService.saveUser(user);

        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        userService.saveUser(user);

        return user;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);

        if(user == null) {
            throw new NotFoundException("User with the id " + id + " doesn't exist in the database");
        }

        userService.deleteUser(id);

        return user;
    }

    @GetMapping("/users/name/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {

        User user = userService.getUserByUsername(username);

        if(user == null) {
            throw new NotFoundException("User not found");
        }

        return user;
    }

    @PostMapping("/users/{user_id}/restaurants/{restaurant_id}")
    public ResponseEntity<String> addRestaurantToFavorite(@PathVariable("user_id")int userId,
                                                          @PathVariable("restaurant_id")long restaurantId) {

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        User user = userService.getUser(userId);

        if(user == null) {
            throw new NotFoundException("Cannot find user");
        }

        if(restaurant == null) {
            throw new NotFoundException("Cannot find restaurant");
        }

        user.addRestaurant(restaurant);
        restaurant.addUser(user);

        userService.saveUser(user);

        return new ResponseEntity<>("Added to favorites", HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}/favorites")
    public Set<Restaurant> getRestaurantFavorites(@PathVariable("user_id")int id) {

        User user = userService.getUser(id);

        if(user == null) {
            throw new NotFoundException("User cannot be found");
        }

        return user.getRestaurantSet();
    }
}
