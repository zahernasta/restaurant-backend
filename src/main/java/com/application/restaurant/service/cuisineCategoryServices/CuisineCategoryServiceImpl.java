package com.application.restaurant.service.cuisineCategoryServices;

import com.application.restaurant.dao.cuisineCategoryDao.CuisineCategoryDAO;
import com.application.restaurant.entity.CuisineCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuisineCategoryServiceImpl implements CuisineCategoryService {

    @Autowired
    CuisineCategoryDAO cuisineCategoryDAO;

    @Override
    public List<CuisineCategory> getAllCuisineCategories() {
        return cuisineCategoryDAO.getAllCuisineCategories();
    }

    @Override
    public CuisineCategory getCuisineById(int id) {
        return cuisineCategoryDAO.getCuisineById(id);
    }

    @Override
    public CuisineCategory getCuisineByName(String name) {
        return cuisineCategoryDAO.getCuisineByName(name);
    }

    @Override
    public void addCuisine(CuisineCategory cuisineCategory) {
        cuisineCategoryDAO.addCuisine(cuisineCategory);
    }

    @Override
    public void deleteCuisine(int id) {
        cuisineCategoryDAO.deleteCuisine(id);
    }
}
