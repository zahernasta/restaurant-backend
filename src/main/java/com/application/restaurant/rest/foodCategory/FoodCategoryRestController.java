package com.application.restaurant.rest.foodCategory;

import com.application.restaurant.entity.FoodCategory;
import com.application.restaurant.rest.exceptions.EmptyFieldsException;
import com.application.restaurant.rest.exceptions.NotFoundException;
import com.application.restaurant.service.foodCategoryServices.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodCategoryRestController {

    @Autowired
    FoodCategoryService foodCategoryService;

    @GetMapping("/categories")
    public List<FoodCategory> getFoodCatgories() {
        List<FoodCategory> list = foodCategoryService.getAllCategories();
        if(list == null) {
            throw new NotFoundException("No Categories has been found");
        }
        return  list;
    }

    @GetMapping("/categories/{id}")
    public FoodCategory getOneFoodById(@PathVariable("id") int id) {
        FoodCategory foodCategory = foodCategoryService.getCategoryById(id);
        if(foodCategory == null) {
            throw new NotFoundException("Food category with the id " + id + " has not been found");
        }
        return foodCategory;
    }

    @PostMapping("/categories")
    public FoodCategory insertFoodCategory(@RequestBody FoodCategory foodCategory) {
        if(foodCategory.getName() == null) {
            throw new EmptyFieldsException("Please fill the category name input");
        }

        foodCategoryService.saveFoodCategory(foodCategory);
        return foodCategory;
    }

    @DeleteMapping("/categories/{id}")
    public FoodCategory deleteFoodCategory(@PathVariable("id") int id) {
        FoodCategory foodCategory = foodCategoryService.getCategoryById(id);
        if(foodCategory == null) {
            throw new NotFoundException("Cannot find category with the respective id");
        }

        foodCategoryService.deleteFoodCategory(id);
        return foodCategory;
    }
}
