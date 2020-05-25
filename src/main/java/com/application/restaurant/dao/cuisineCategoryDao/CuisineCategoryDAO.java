package com.application.restaurant.dao.cuisineCategoryDao;

import com.application.restaurant.entity.CuisineCategory;

import java.util.List;

public interface CuisineCategoryDAO {

    List<CuisineCategory> getAllCuisineCategories();

    CuisineCategory getCuisineById(int id);

    CuisineCategory getCuisineByName(String name);

    void addCuisine(CuisineCategory cuisineCategory);

    void deleteCuisine(int id);

}
