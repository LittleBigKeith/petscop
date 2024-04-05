package com.fdmgroup.petscop.model;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "OWNER_FOOD")
public class OwnerFood {
	
	@EmbeddedId
	private OwnerFoodEmbeddedKey ownerFoodEmbeddedKey;
	
	// additional field
	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	public OwnerFood(Owner owner, Food food, int quantity) {
		this.ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(owner, food);
		this.quantity = quantity;
	}
	
	public OwnerFood() {
		
	}
	
	public OwnerFoodEmbeddedKey getId() {
		return ownerFoodEmbeddedKey;
	}

	public void setId(OwnerFoodEmbeddedKey ownerFoodEmbeddedKey) {
		this.ownerFoodEmbeddedKey = ownerFoodEmbeddedKey;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OwnerFood [ownerFoodEmbeddedKey=" + ownerFoodEmbeddedKey + ", quantity=" + quantity + "]";
	}
	
	
}
