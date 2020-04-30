package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    )
    @JoinTable(
            name= "restaurants_photos",
            joinColumns=@JoinColumn(name="photo_id"),
            inverseJoinColumns=@JoinColumn(name="restaurant_id")
    )
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Restaurant> restaurantList;

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

    public Set<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(Set<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public void addRestaurant(Restaurant restaurant) {

        if(this.restaurantList == null) {
            restaurantList = new HashSet<>();
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
