package com.application.restaurant.entity;

import javax.persistence.*;
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
	private Set<Basket> basket;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Order> orderSet;

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
}





