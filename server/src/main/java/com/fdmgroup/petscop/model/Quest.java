package com.fdmgroup.petscop.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "QUEST")
public class Quest {
	@Id
	@GeneratedValue
	@Column(name = "QUEST_ID", nullable = false)
	private int id;
	@Column(name = "QUEST_NAME", nullable = false, unique = true)
	private String name;
	@Column(name = "COST", nullable = false)
	private int cost;
	@Column(name = "MIN_REWARD", nullable = false)
	private int minReward;
	@Column(name = "MAX_REWARD", nullable = false)
	private int maxReward;
	
	public Quest(String name, int cost, int minReward, int maxReward) {
		super();
		this.name = name;
		this.cost = cost;
		this.minReward = minReward;
		this.maxReward = maxReward;
	}
	
	public Quest() {
		
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getMinReward() {
		return minReward;
	}
	public void setMinReward(int minReward) {
		this.minReward = minReward;
	}
	
	public int getMaxReward() {
		return maxReward;
	}
	public void setMaxReward(int reward) {
		this.maxReward = reward;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cost, id, name, minReward, maxReward);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quest other = (Quest) obj;
		return cost == other.cost && id == other.id && Objects.equals(name, other.name) && minReward == other.minReward && maxReward == other.maxReward;
	}
	@Override
	public String toString() {
		return "Quest [id=" + id + ", name=" + name + ", cost=" + cost + ", minReward=" + minReward + ", maxReward=" + maxReward + "]";
	}
	
	
	
}
