package com.application.restaurant.rest.food;

import com.application.restaurant.entity.Food;
import com.application.restaurant.entity.FoodCategory;
import com.application.restaurant.rest.exceptions.EmptyFieldsException;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.foodCategoryServices.FoodCategoryService;
import com.application.restaurant.service.foodServices.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodRestController {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodCategoryService foodCategoryService;

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        List<Food> foodsList = foodService.getAllFoods();
        if(foodsList == null) {
            throw new NotFoundException("No foods were found");
        }

        return foodsList;
    }

    @GetMapping("/foods/{id}")
    public Food getOneFoodById(@PathVariable("id") int id) {
        Food food = foodService.getFoodById(id);
        if(food == null) {
            throw new NotFoundException("Food with id " + id + " has not been found");
        }

        return food;
    }

    @GetMapping("/foods/{id}/category")
    public FoodCategory getFoodCategory(@PathVariable("id") int id) {
        Food food = foodService.getFoodById(id);
        if(food == null) {
            throw new NotFoundException("Food with id " + id + " has not been found");
        }

        return food.getFoodCategory();
    }

    @PostMapping("/foods")
    public Food insertOneFood(@RequestBody Food food, @RequestParam(name ="category") String category) {

        if(category == null) {
            throw new EmptyFieldsException("Please fill your category row");
        }

        FoodCategory foodCategory = foodCategoryService.getCategoryByName(category);
        if(foodCategory == null) {
            throw new NotFoundException("Category with the name " + category +
                    " has not been found");
        }
        foodCategory.addFood(food);
        foodService.saveFood(food);

        return food;
    }

    @PutMapping("/foods")
    public Food updateFood(@RequestBody Food food) {
        if(foodService.getFoodById(food.getId()) == null) {
            throw new NotFoundException("No food has been found with this id");
        }
        foodService.updateFood(food);
        return food;
    }

    @DeleteMapping("/foods/{id}")
    public Food deleteFood(@PathVariable("id") int id) {
        Food food = foodService.getFoodById(id);
        if(food == null) {
            throw new NotFoundException("Food with the respective id has not been found");
        }
        foodService.deleteFood(id);
        return food;
    }

}
