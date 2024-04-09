package com.fdmgroup.petscop.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OwnerFoodEmbeddedKey {

	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "OWNER_ID") 
	private Owner owner;
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "FOOD_ID") 
	private Food food;	
	
	public OwnerFoodEmbeddedKey(Owner owner, Food food) {
		this.owner = owner;
		this.food = food;
	}
	
	public OwnerFoodEmbeddedKey() {
		
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Food getFood() {
		return food;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(food, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OwnerFoodEmbeddedKey other = (OwnerFoodEmbeddedKey) obj;
		return Objects.equals(food, other.food) && Objects.equals(owner, other.owner);
	}

	@Override
	public String toString() {
		return "OwnerFoodEmbeddedKey [owner=" + owner + ", food=" + food + "]";
	}
	
	
}
