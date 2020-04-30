package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="address")
    @NotNull
    private String address;

    @Column(name="delivery_start_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date deliveryStartTime;

    @Column(name="delivery_end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date deliveryEndTime;

    @Column(name="delivery_costs")
    @NotNull
    private double deliveryCosts;

    @Column(name="restaurant_start_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date restaurantStartTime;

    @Column(name="restaurant_end_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date restaurantEndTime;

    @Column(name="phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="description")
    @NotNull
    private String description;

    @ManyToMany(
            fetch=FetchType.EAGER
    )
    @JoinTable(
            name= "restaurants_photos",
            joinColumns=@JoinColumn(name="restaurant_id"),
            inverseJoinColumns=@JoinColumn(name="photo_id")
    )
    @JsonIgnore
    private List<Photo> photoList;

    public Restaurant() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(Date deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public Date getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(Date deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public double getDeliveryCosts() {
        return deliveryCosts;
    }

    public void setDeliveryCosts(double deliveryCosts) {
        this.deliveryCosts = deliveryCosts;
    }

    public Date getRestaurantStartTime() {
        return restaurantStartTime;
    }

    public void setRestaurantStartTime(Date restaurantStartTime) {
        this.restaurantStartTime = restaurantStartTime;
    }

    public Date getRestaurantEndTime() {
        return restaurantEndTime;
    }

    public void setRestaurantEndTime(Date restaurantEndTime) {
        this.restaurantEndTime = restaurantEndTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void addPhoto(Photo photo) {

        if(this.photoList == null) {
            photoList = new ArrayList<>();
        }

        photoList.add(photo);
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
