package com.application.restaurant.service.photosServices;

import com.application.restaurant.entity.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> getAllPhotos();

    void savePhoto(Photo photo);

    Photo getPhoto(long id);

    Photo getPhotoByName(String name);

    void deletePhoto(long id);
}
