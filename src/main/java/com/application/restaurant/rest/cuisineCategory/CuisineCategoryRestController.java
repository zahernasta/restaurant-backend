package com.application.restaurant.rest.cuisineCategory;


import com.application.restaurant.entity.CuisineCategory;
import com.application.restaurant.service.cuisineCategoryServices.CuisineCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuisineCategoryRestController {

    @Autowired
    CuisineCategoryService cuisineCategoryService;

    @GetMapping("/cuisine")
    public List<CuisineCategory> getAllCategories() {
        return cuisineCategoryService.getAllCuisineCategories();
    }
}
