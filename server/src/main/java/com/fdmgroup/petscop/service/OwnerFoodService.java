package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	public OwnerFood findByKey(int ownerId, int foodId) {
		OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(ownerRepo.findById(ownerId).get(), foodRepo.findById(foodId).get());
		return ownerFoodRepo.findByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey).get();
	}
	
	public List<OwnerFood> findByOwnerId(int ownerId) {
		return ownerFoodRepo.findByOwnerFoodEmbeddedKeyOwner(ownerRepo.findById(ownerId).get());
	}
	
	public List<OwnerFood> findAll() {
		return ownerFoodRepo.findAll();
	}
	
	public void create(OwnerFood ownerFood) {
		ownerFoodRepo.save(ownerFood);
	}
	
	public void update(@RequestBody OwnerFood ownerFood) {
		ownerFoodRepo.save(ownerFood);
	}
	
	public void deleteByKey(int ownerId, int foodId) {
		OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(ownerRepo.findById(ownerId).get(), foodRepo.findById(foodId).get());
		ownerFoodRepo.deleteByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey);
	}
	
}
