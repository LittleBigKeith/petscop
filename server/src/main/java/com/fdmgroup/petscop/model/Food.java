package com.fdmgroup.petscop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOOD")
public class Food {
	@Id
	@GeneratedValue
	@Column(name = "FOOD_ID", nullable = false)
	private int id;
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	@Column(name = "PRICE", nullable = false)
	private int price;
	@Column(name = "SATURATION", nullable = false)
	private int saturation;

	public Food(String name, int price, int saturation) {
		this.name = name;
		this.price = price;
		this.saturation = saturation;
	}
	
	public Food() {
		
	}

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSaturation() {
		return saturation;
	}

	public void setSaturation(int saturation) {
		this.saturation = saturation;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", price=" + price + ", saturation=" + saturation + "]";
	}	
	
}
