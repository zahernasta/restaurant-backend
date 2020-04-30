package com.application.restaurant.rest.photo;

import com.application.restaurant.entity.Photo;
import com.application.restaurant.service.photosServices.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/api")
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;

    private Photo photo;

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/photos/id/{id}")
    public Photo getOnePhoto(@PathVariable long id) {
        return photoService.getPhoto(id);
    }

    //TODO: Test the photo controller to see if it does its job
    @GetMapping("/photos/name/{name}")
    public Photo getOnePhotoByName(@PathVariable String name) {

        return photoService.getPhotoByName(name);
    }

    @PostMapping("/photos")
    public Photo addPhoto(@RequestParam("imageFile") MultipartFile file) throws IOException {

        //TODO: Hash the name when inserting in database
        Photo photo = new Photo();
        photo.setId(0);
        File currDir = new File(".");
        String absolutePath = currDir.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.length()-1);
//
        String preFolder = "src/main/resources/static/";
        String folder = "photos/";
        byte[] imageBytes = file.getBytes();
//
        Path path = Paths.get(absolutePath + preFolder + folder + file.getOriginalFilename());
        System.out.println(path);
        photo.setName(file.getOriginalFilename());
        photo.setExtension(file.getContentType());
        photo.setPhotoLocation(folder + file.getOriginalFilename());

        System.out.println(photo.getPhotoLocation());
        Files.write(path, imageBytes);
        photoService.savePhoto(photo);
        return photo;
    }

    @PutMapping("/photos")
    public Photo updatePhoto(@RequestBody Photo photo) {
        photoService.savePhoto(photo);

        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable long id) {
        photoService.deletePhoto(id);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
