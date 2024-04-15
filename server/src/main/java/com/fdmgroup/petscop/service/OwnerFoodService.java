package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.dal.FoodRepository;
import com.fdmgroup.petscop.dal.OwnerFoodRepository;
import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.model.OwnerFoodEmbeddedKey;

@Service
public class OwnerFoodService {
	
	private OwnerFoodRepository ownerFoodRepo;
	private OwnerRepository ownerRepo;
	private FoodRepository foodRepo;

	@Autowired
	public OwnerFoodService(OwnerFoodRepository ownerFoodRepo, OwnerRepository ownerRepo, FoodRepository foodRepo) {
		super();
		this.ownerFoodRepo = ownerFoodRepo;
		this.ownerRepo = ownerRepo;
		this.foodRepo = foodRepo;
	}
	
	public OwnerFood findByKey(String username, String foodname) {
		OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(ownerRepo.findByUsername(username).get(), foodRepo.findByName(foodname).get());
		return ownerFoodRepo.findByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey).get();
	}
	
	public List<OwnerFood> findByOwner(String username) {
		return ownerFoodRepo.findByOwnerFoodEmbeddedKeyOwner(ownerRepo.findByUsername(username).get());
	}
	
	public List<OwnerFood> findAll() {
		return ownerFoodRepo.findAll();
	}
	
	public void createOrUpdate(OwnerFood ownerFood) {
		if (ownerFood.getQuantity() <= 0) {
			System.out.println(ownerFood.getQuantity());
			ownerFoodRepo.deleteByOwnerFoodEmbeddedKey(ownerFood.getId());
		} else {
			ownerFoodRepo.save(ownerFood);
		}
	}
	
	public void deleteByKey(int ownerId, int foodId) {
		OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(ownerRepo.findById(ownerId).get(), foodRepo.findById(foodId).get());
		ownerFoodRepo.deleteByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey);
	}
	
}
