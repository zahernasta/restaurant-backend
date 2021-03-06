package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

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
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Photo> photoList;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "menu",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Food> foodList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "favorite",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<User> userSet;

    @OneToOne(mappedBy = "restaurant")
    @JsonIgnore
    private Basket basket;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonIgnore
    private Set<Order> orderSet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuisine_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull
    private CuisineCategory cuisineCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonIgnore
    private Set<Message> messageSet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonIgnore
    private Set<Reservation> reservationSet;

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

    public Set<Photo> getPhotoList() {
        return photoList;
    }

    public void addPhoto(Photo photo) {

        if(this.photoList == null) {
            photoList = new HashSet<>();
        }

        photoList.add(photo);
    }

    public void setPhotoList(Set<Photo> photoList) {
        this.photoList = photoList;
    }

    public Set<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(Set<Food> foodList) {
        this.foodList = foodList;
    }

    public void addFood(Food food) {
        if(this.foodList == null) {
            this.foodList = new HashSet<>();
        }

        this.foodList.add(food);
    }


    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public void addOrder(Order order) {
        if(this.orderSet == null) {
            this.orderSet = new HashSet<>();
        }

        this.orderSet.add(order);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public void addUser (User user) {
        if(this.userSet == null) {
            this.userSet = new HashSet<>();
        }
        this.userSet.add(user);
    }

    public CuisineCategory getCuisineCategory() {
        return cuisineCategory;
    }

    public void setCuisineCategory(CuisineCategory cuisineCategory) {
        this.cuisineCategory = cuisineCategory;
    }

    public Set<Message> getMessageSet() {
        return messageSet;
    }

    public void addMessage (Message message) {
        if(this.messageSet == null) {
            this.messageSet = new HashSet<>();
        }
        this.messageSet.add(message);
    }

    public void setMessageSet(Set<Message> messageSet) {
        this.messageSet = messageSet;
    }

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void addReservation(Reservation reservation) {
        if(this.reservationSet == null) {
            this.reservationSet = new HashSet<>();
        }
        this.reservationSet.add(reservation);
    }
}
