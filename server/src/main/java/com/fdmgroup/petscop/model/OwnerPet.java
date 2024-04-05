package com.fdmgroup.petscop.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "OWNER_PET")
public class OwnerPet {
	@Id
	@GeneratedValue
	@Column(name = "OWNER_PET_ID", nullable = false)
	private int id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "OWNER_ID")
	private Owner owner;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PET_ID")
	private Pet pet;

	// additional fields
	@Column(name = "GIVEN_NAME")
	private String givenName;
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE", nullable = false)
	private LocalDate birthDate;
	@Column(name = "HUNGER_POINT", nullable = false)
	private int hungerPoint;

	public OwnerPet(Owner owner, Pet pet, String givenName, LocalDate birthDate, int hungerPoint) {
		this.owner = owner;
		this.pet = pet;
		this.givenName = givenName;
		this.birthDate = birthDate;
		this.hungerPoint = hungerPoint;
	}

	public OwnerPet() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getHungerPoint() {
		return hungerPoint;
	}

	public void setHungerPoint(int hungerPoint) {
		this.hungerPoint = hungerPoint;
	}
	
	@Override
	public String toString() {
		return "OwnerPet [id=" + id + ", owner=" + owner + ", pet=" + pet + ", givenName=" + givenName + ", birthDate="
				+ birthDate + ", hungerPoint=" + hungerPoint + "]";
	}
	
	
}
