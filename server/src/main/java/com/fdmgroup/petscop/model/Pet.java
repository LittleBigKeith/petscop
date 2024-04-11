package com.fdmgroup.petscop.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PET")
public class Pet {
	@Id
	@GeneratedValue
	@Column(name = "PET_ID", nullable = false)
	private int id;
	@Column(name = "DEFAULT_NAME", nullable = false, unique = true)
	private String defaultName;
	@Column(name = "PRICE", nullable = false)
	private int price;

	@Column(name = "MAX_HUNGER_POINT", nullable = false)
	private int maxHungerPoint;

	public Pet(String defaultName, int price, int maxHungerPoint) {
		this.defaultName = defaultName;
		this.price = price;
		this.maxHungerPoint = maxHungerPoint;
	}
	
	public Pet() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMaxHungerPoint() {
		return maxHungerPoint;
	}

	public void setHunger_point(int maxHungerPoint) {
		this.maxHungerPoint = maxHungerPoint;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(defaultName, id, maxHungerPoint, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(defaultName, other.defaultName) && id == other.id
				&& maxHungerPoint == other.maxHungerPoint && price == other.price;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", defaultName=" + defaultName + ", price=" + price
				+ ", maxHungerPoint=" + maxHungerPoint + "]";
	}
	
}
