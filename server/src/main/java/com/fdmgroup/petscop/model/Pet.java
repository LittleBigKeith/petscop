package com.fdmgroup.petscop.model;

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
	public enum Gender {
		M, F
	}

	@Id
	@GeneratedValue
	@Column(name = "PET_ID", nullable = false)
	private int id;
	@Column(name = "DEFAULT_NAME", nullable = false, unique = true)
	private String defaultName;
	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER", nullable = false)
	private Gender gender;
	@Column(name = "PRICE", nullable = false)
	private int price;

	@Column(name = "MAX_HUNGER_POINT", nullable = false)
	private int maxHungerPoint;

	public Pet(String defaultName, Gender gender, int price, int maxHungerPoint) {
		this.defaultName = defaultName;
		this.gender = gender;
		this.price = price;
		this.maxHungerPoint = maxHungerPoint;
	}
	
	public Pet() {
		
	}
	
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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
	public String toString() {
		return "Pet [id=" + id + ", defaultName=" + defaultName + ", gender=" + gender + ", price=" + price
				+ ", maxHungerPoint=" + maxHungerPoint + "]";
	}
	
}
