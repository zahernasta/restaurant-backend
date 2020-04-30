package com.application.restaurant.rest.restaurant;

import com.application.restaurant.entity.Photo;
import com.application.restaurant.entity.Restaurant;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.photosServices.PhotoService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.userServices.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PhotoService photoService;


    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getOneRestaurant(@PathVariable long id) {

        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {

        restaurant.setId(0);

        restaurantService.saveRestaurant(restaurant);

        return restaurant;
    }

    //TODO: Check this later when working on the admin page c
    @PostMapping("/restaurants/{id}/photo")
    public Photo addRestaurantPhoto(@PathVariable long id, @RequestParam("imageFile") MultipartFile file) throws IOException {


        Photo photo = new Photo();
        photo.setId(0);
        File currDir = new File(".");
        String absolutePath = currDir.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.length()-1);

        String preFolder = "src/main/resources/static/";
        String folder = "photos/";
        byte[] imageBytes = file.getBytes();

        Path path = Paths.get(absolutePath + preFolder + folder + file.getOriginalFilename());

        Restaurant restaurant = restaurantService.getRestaurant(id);
        photo.setName(file.getOriginalFilename());
        photo.setExtension(file.getContentType());
        photo.setPhotoLocation(folder + file.getOriginalFilename());
        photo.addRestaurant(restaurant);
//        restaurant.addPhoto(photo);
        Files.write(path, imageBytes);
        photoService.savePhoto(photo);
        return photo;
    }

    @GetMapping("/restaurants/{id}/photo")
    public Set<Photo> getPhotosByRestaurantId(@PathVariable long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant.getPhotoList();
    }

    @GetMapping("/restaurants/{id}/photo/{photoId}")
    public Photo getPhotoByIdFromRestaurant(@PathVariable("id") long id, @PathVariable("photoId") long photoId) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        for(Photo photo : restaurant.getPhotoList()) {
            if(photo.getId() == photoId) {
                return photo;
            }
        }

        throw new NotFoundException("Photo with the id " + photoId + " cannot be found");
    }

    @PutMapping("/restaurants")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {

        restaurantService.updateRestaurant(restaurant);

        return restaurant;
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable long id) {

        restaurantService.deleteRestaurant(id);
    }
}
