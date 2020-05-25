package com.application.restaurant.rest.restaurant;

import com.application.restaurant.entity.*;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.cuisineCategoryServices.CuisineCategoryService;
import com.application.restaurant.service.foodCategoryServices.FoodCategoryService;
import com.application.restaurant.service.foodServices.FoodService;
import com.application.restaurant.service.photosServices.PhotoService;
import com.application.restaurant.service.restaurantServices.RestaurantService;
import com.application.restaurant.service.restaurantServices.RestaurantServiceImpl;
import com.application.restaurant.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private CuisineCategoryService cuisineCategoryService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getOneRestaurant(@PathVariable long id) {

        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant,
                                    @RequestParam(name = "category") String category) {

        restaurant.setId(0);

        CuisineCategory cuisineCategory = cuisineCategoryService.getCuisineByName(category);
        if(cuisineCategory == null) {
            throw new NotFoundException("Category not found");
        }

        cuisineCategory.addRestaurant(restaurant);
        restaurantService.saveRestaurant(restaurant);

        return restaurant;
    }

    @PostMapping("/restaurants/{id}/photo")
    public Photo addRestaurantPhoto(@PathVariable long id, @RequestParam("imageFile") MultipartFile file) throws IOException {


        Photo photo = new Photo();
        photo.setId(0);
        File currDir = new File(".");
        String absolutePath = currDir.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.length()-1);

        //TODO: Put them in a static final variable
        String preFolder = "src/main/resources/static/";
        String folder = "photos/";
        byte[] imageBytes = file.getBytes();

        Path path = Paths.get(absolutePath + preFolder + folder + file.getOriginalFilename());

        Restaurant restaurant = restaurantService.getRestaurant(id);
        photo.setName(file.getOriginalFilename());
        photo.setExtension(file.getContentType());
        photo.setPhotoLocation(folder + file.getOriginalFilename());
        photo.addRestaurant(restaurant);
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

    @GetMapping("/restaurants/categories")
    public List<Restaurant> getAllRestaurantsByCategory(@RequestParam("category") String category) {

        CuisineCategory cuisineCategory = cuisineCategoryService.getCuisineByName(category);
        if(cuisineCategory == null) {
            throw new NotFoundException("Category not found");
        }

        List<Restaurant> restaurants = restaurantService.getAllRestaurantByCuisineId(cuisineCategory.getId());

        return restaurants;
    }

    @PostMapping("/restaurants/{id}/menu")
    public Food insertFoodToMenu(@PathVariable("id") long id, @RequestParam("category") String category,
                                 @RequestBody Food food) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if(restaurant == null) {
            throw new NotFoundException("Restaurant with this id has not been found");
        }

        FoodCategory foodCategory = foodCategoryService.getCategoryByName(category);
        if(foodCategory == null) {
            throw new NotFoundException("Category of this respective name has not been found");
        }

        foodCategory.addFood(food);
        food.addRestaurant(restaurant);
        foodService.saveFood(food);

        return food;
    }

    @GetMapping("restaurants/{id}/menu/{foodId}")
    public Food getFoodFromMenuById(@PathVariable("id") long id, @PathVariable("foodId") int foodId) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        for(Food food : restaurant.getFoodList()) {
            if(food.getId() == foodId) {
                return food;
            }
        }
        throw new NotFoundException("Food with that id has not been found");
    }

    @GetMapping("restaurants/{id}/menu")
    public Set<Food> getFoodFromMenu(@PathVariable("id") long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant.getFoodList();
    }

    @DeleteMapping("restaurants/{id}/menu/{foodId}")
    public ResponseEntity<String> deleteFoodFromMenu(@PathVariable("id") long id, @PathVariable("foodId") int foodId) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        for(Food food : restaurant.getFoodList()) {
            if(food.getId() == foodId) {
                foodService.deleteFood(foodId);
                return new ResponseEntity<>("Food deleted successfully", HttpStatus.OK);
            }
        }
        throw new NotFoundException("Food with that id has not been found");
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
