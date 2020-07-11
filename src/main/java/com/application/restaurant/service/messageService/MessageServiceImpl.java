package com.application.restaurant.service.messageService;

import com.application.restaurant.dao.messageDao.MessageDAO;
import com.application.restaurant.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageDAO messageDAO;


    @Override
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
}
