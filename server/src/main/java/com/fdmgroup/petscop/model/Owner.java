package com.fdmgroup.petscop.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	public enum Role {
		User, Admin
	}
	@Id
	@GeneratedValue
	@Column(name = "OWNER_ID", nullable = false)
	private int id;
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "ROLE", nullable = false)
	private Owner.Role role;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd", shape=JsonFormat.Shape.STRING)
	@Column(name = "CAKE_DATE", nullable = false)
	private LocalDate cakeDate;
	@Column(name = "GOLD", nullable = false)
	private int gold;
	@Column(name = "EXPERIENCE", nullable = false)
	private int experience;

	public Owner(String username, String password, Owner.Role role, LocalDate cakeDate, int gold, int experience) {
		this.username = username;
		this.password = password;
		this.cakeDate = cakeDate;
		this.role = role;
		this.gold = gold;
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
	
	public Owner.Role getRole() {
		return role;
	}
	
	public void setRole(Owner.Role role) {
		this.role = role;
	}
		
	public LocalDate getCakeDate() {
		return cakeDate;
	}

	public void setCakeDate(LocalDate cakeDate) {
		this.cakeDate = cakeDate;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cakeDate, experience, id, gold, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Owner other = (Owner) obj;
		return Objects.equals(cakeDate, other.cakeDate) && experience == other.experience && id == other.id
				&& gold == other.gold && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", cakeDate=" + cakeDate
				+ ", gold=" + gold + ", experience=" + experience + "]";
	}
	
	
}
