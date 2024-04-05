package com.fdmgroup.petscop.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "OWNER")
public class Owner {
	@Id
	@GeneratedValue
	@Column(name = "OWNER_ID", nullable = false)
	private int id;
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name = "CAKE_DATE", nullable = false)
	private LocalDate cakeDate;
	@Column(name = "IN_GAME_WEALTH", nullable = false)
	private int inGameWealth;
	@Column(name = "EXPERIENCE", nullable = false)
	private int experience;

	public Owner(String username, String password, LocalDate cakeDate, int inGameWealth, int experience) {
		this.username = username;
		this.password = password;
		this.cakeDate = cakeDate;
		this.inGameWealth = inGameWealth;
		this.experience = experience;
	}
	
	public Owner() {
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCakeDate() {
		return cakeDate;
	}

	public void setCakeDate(LocalDate cakeDate) {
		this.cakeDate = cakeDate;
	}

	public int getInGameWealth() {
		return inGameWealth;
	}

	public void setInGameWealth(int inGameWealth) {
		this.inGameWealth = inGameWealth;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", username=" + username + ", password=" + password + ", cakeDate=" + cakeDate
				+ ", inGameWealth=" + inGameWealth + ", experience=" + experience + "]";
	}
	
	
}
