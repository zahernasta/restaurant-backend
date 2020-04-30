package com.application.restaurant.service.userServices;

import java.util.List;

import com.application.restaurant.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
}
