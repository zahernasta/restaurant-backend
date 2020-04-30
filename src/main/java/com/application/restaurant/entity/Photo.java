package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="photos")
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="photoLocation")
    @NotNull
    private String photoLocation;

    @Column(name="extension")
    @NotNull
    private String extension; 

    @Column(name="name")
    @NotNull
    private String name;

    @ManyToMany(
            fetch=FetchType.EAGER
//            ,
//             mappedBy = "photoList"
    )
    @JoinTable(
            name= "restaurants_photos",
            joinColumns=@JoinColumn(name="photo_id"),
            inverseJoinColumns=@JoinColumn(name="restaurant_id")
    )
    @JsonIgnore
    private List<Restaurant> restaurantList;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public void addRestaurant(Restaurant restaurant) {

        if(this.restaurantList == null) {
            restaurantList = new ArrayList<>();
        }

        restaurantList.add(restaurant);
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
