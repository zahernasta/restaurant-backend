package com.application.restaurant.service.photosServices;

import com.application.restaurant.dao.photosDao.PhotoDAO;
import com.application.restaurant.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoDAO photoDAO;

    @Override
    public List<Photo> getAllPhotos() {
        return photoDAO.getAllPhotos();
    }

    @Override
    public void savePhoto(Photo photo) {
        photoDAO.savePhoto(photo);
    }

    @Override
    public Photo getPhoto(long id) {
        return photoDAO.getPhoto(id);
    }

    @Override
    public Photo getPhotoByName(String name) {
        return photoDAO.getPhotoByName(name);
    }

    @Override
    public void deletePhoto(long id) {
        photoDAO.deletePhoto(id);
    }
}
