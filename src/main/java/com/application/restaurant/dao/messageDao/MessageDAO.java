package com.application.restaurant.dao.messageDao;

import com.application.restaurant.entity.Message;
import com.application.restaurant.entity.User;

import java.util.List;

public interface MessageDAO {

    List<Message> getAllMessages();

}
