package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name="cuisine_categories")
@Entity
public class CuisineCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cuisineCategory")
    @JsonIgnore
    private Set<Restaurant> restaurantSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cuisine_categories_photos",
            joinColumns = @JoinColumn(name =  "cuisine_categories_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Photo> photoSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Restaurant> getRestaurantSet() {
        return restaurantSet;
    }

    public void setRestaurantSet(Set<Restaurant> restaurantSet) {
        this.restaurantSet = restaurantSet;
    }

    public void addRestaurant(Restaurant restaurant) {
        if(this.restaurantSet == null) {
            this.restaurantSet = new HashSet<>();
        }

        this.restaurantSet.add(restaurant);
    }

    public Set<Photo> getPhotoSet() {
        return photoSet;
    }

    public void addPhoto(Photo photo) {
        if(this.photoSet == null) {
            this.photoSet = new HashSet<>();
        }
        this.photoSet.add(photo);
    }
}
