package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date orderDate;

    @Column(name = "order_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @NotNull
    private Date orderTime;

    @Column(name = "delivery_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "delivery_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date deliveryTime;

    @Column(name = "order_status")
    @NotNull
    private boolean orderStatus;

    @Column(name = "order_cancelled")
    @NotNull
    private boolean orderCancelled;

    @Column(name = "amount")
    @NotNull
    private float amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    @NotNull
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isOrderCancelled() {
        return orderCancelled;
    }

    public void setOrderCancelled(boolean orderCancelled) {
        this.orderCancelled = orderCancelled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
