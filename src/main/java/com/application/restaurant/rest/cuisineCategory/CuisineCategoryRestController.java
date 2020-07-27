package com.application.restaurant.rest.cuisineCategory;


import com.application.restaurant.entity.CuisineCategory;
import com.application.restaurant.entity.Photo;
import com.application.restaurant.service.cuisineCategoryServices.CuisineCategoryService;
import com.application.restaurant.service.photosServices.PhotoService;
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

@RestController
@RequestMapping("/api")
public class CuisineCategoryRestController {

    @Autowired
    CuisineCategoryService cuisineCategoryService;

    @Autowired
    PhotoService photoService;

    @GetMapping("/cuisines")
    public List<CuisineCategory> getAllCategories() {
        return cuisineCategoryService.getAllCuisineCategories();
    }

    @PostMapping("/cuisines/{id}/photo")
    public ResponseEntity<String> insertCuisineCategoryPhoto(@PathVariable("id") int cuisineId,
                                                             @RequestParam("imageFile") MultipartFile file)
            throws IOException {

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

        CuisineCategory cuisineCategory = cuisineCategoryService.getCuisineById(cuisineId);

        photo.setName(file.getOriginalFilename());
        photo.setExtension(file.getContentType());
        photo.setPhotoLocation(folder + file.getOriginalFilename());
        photo.addCuisineCategory(cuisineCategory);
        Files.write(path, imageBytes);
        photoService.savePhoto(photo);

        return new ResponseEntity<>("Photo added successfully", HttpStatus.OK);
    }
}
