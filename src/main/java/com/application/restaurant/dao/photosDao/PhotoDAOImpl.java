package com.application.restaurant.dao.photosDao;

import com.application.restaurant.entity.Photo;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PhotoDAOImpl implements PhotoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Photo> getAllPhotos() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Photo> photoQuery = currentSession.createQuery("from Photo ");

        List<Photo> photos = photoQuery.getResultList();

        return photos;
    }

    @Override
    public void savePhoto(Photo photo) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(photo);
    }

    @Override
    public Photo getPhoto(long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Photo photo = currentSession.get(Photo.class, id);

        return photo;

    }

    @Override
    public Photo getPhotoByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Photo> photoQuery = currentSession.createQuery("from Photo where name=:name");

        photoQuery.setParameter("name", name);

        Photo photo = photoQuery.getSingleResult();
        return photo;
    }

    @Override
    public void deletePhoto(long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery("delete from Photo where id=:photoId");

        query.setParameter("photoId", id);

        query.executeUpdate();
    }
}
