package com.application.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="username")
	private String username;

	@Column(name="email")
	private String email;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Basket> basket;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<Order> orderSet;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "favorite",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "restaurant_id")
	)
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Restaurant> restaurantSet;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<Message> messageSet;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<Reservation> reservationSet;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Basket> getBasket() {
		return basket;
	}

	public void setBasket(Set<Basket> basket) {
		this.basket = basket;
	}

	public void addNewBasket(Basket newBasket) {
		if(this.basket == null) {
			this.basket = new HashSet<>();
		}

		this.basket.add(newBasket);
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	public void addNewOrder(Order order ) {
		if(this.orderSet == null) {
			this.orderSet = new HashSet<>();
		}

		this.orderSet.add(order);
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

	public void addReservation (Reservation reservation) {
		if(this.reservationSet == null) {
			this.reservationSet = new HashSet<>();
		}
		this.reservationSet.add(reservation);
	}
}





