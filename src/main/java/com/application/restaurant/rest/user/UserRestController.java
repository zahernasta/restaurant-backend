package com.application.restaurant.rest.user;

import com.application.restaurant.entity.User;
import com.application.restaurant.rest.exceptions.EmptyFieldsException;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

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
}
