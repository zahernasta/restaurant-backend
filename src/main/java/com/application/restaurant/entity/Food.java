package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "food_name")
    @NotNull
    private String foodName;

    @Column(name = "food_ingredients")
    @NotNull
    private String foodIngredients;

    @Column(name = "food_quantity")
    @NotNull
    private int foodQuantity;

    @Column(name = "food_price")
    @NotNull
    private float foodPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull
    private FoodCategory foodCategory;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name="menu",
            joinColumns = @JoinColumn(name="food_id"),
            inverseJoinColumns = @JoinColumn(name="restaurant_id")

    )
    @JsonIgnore
    private Set<Restaurant> restaurantList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodIngredients() {
        return foodIngredients;
    }

    public void setFoodIngredients(String foodIngredients) {
        this.foodIngredients = foodIngredients;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public Set<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(Set<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public void addRestaurant(Restaurant restaurant) {

        if(this.restaurantList == null) {
            this.restaurantList = new HashSet<>();
        }

        restaurantList.add(restaurant);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

}
