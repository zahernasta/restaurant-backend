package com.application.restaurant.service.cuisineCategoryServices;

import com.application.restaurant.entity.CuisineCategory;

import java.util.List;

public interface CuisineCategoryService {

    List<CuisineCategory> getAllCuisineCategories();

    CuisineCategory getCuisineById(int id);

    CuisineCategory getCuisineByName(String name);

    void addCuisine(CuisineCategory cuisineCategory);

    void deleteCuisine(int id);
}
