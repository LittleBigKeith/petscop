package com.fdmgroup.petscop.model;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JoinColumn(name = "USERNAME", referencedColumnName="USERNAME")
	private Owner owner;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "DEFAULT_NAME", referencedColumnName="DEFAULT_NAME")
	private Pet pet;

	// additional fields
	@Column(name = "GIVEN_NAME")
	private String givenName;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
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
	public int hashCode() {
		return Objects.hash(birthDate, givenName, hungerPoint, id, owner, pet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OwnerPet other = (OwnerPet) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(givenName, other.givenName)
				&& hungerPoint == other.hungerPoint && id == other.id && Objects.equals(owner, other.owner)
				&& Objects.equals(pet, other.pet);
	}

	@Override
	public String toString() {
		return "OwnerPet [id=" + id + ", owner=" + owner + ", pet=" + pet + ", givenName=" + givenName + ", birthDate="
				+ birthDate + ", hungerPoint=" + hungerPoint + "]";
	}
	
	
}
