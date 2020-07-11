package com.application.restaurant.dao.messageDao;

import com.application.restaurant.entity.Message;
import com.application.restaurant.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Message> getAllMessages() {
        Session session = sessionFactory.getCurrentSession();

        Query<Message> messageQuery = session.createQuery("from messages");

        List<Message> messageList = messageQuery.getResultList();

        return messageList;

    }

}
