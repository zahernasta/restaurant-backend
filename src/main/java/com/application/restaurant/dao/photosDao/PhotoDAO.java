package com.application.restaurant.dao.photosDao;


import com.application.restaurant.entity.Photo;

import java.util.List;

public interface PhotoDAO {

    List<Photo> getAllPhotos();

    void savePhoto(Photo photo);

    Photo getPhoto(long id);

    Photo getPhotoByName(String name);

    void deletePhoto(long id);

}
