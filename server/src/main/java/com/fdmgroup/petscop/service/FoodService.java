package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.petscop.dal.FoodRepository;
import com.fdmgroup.petscop.model.Food;

@Service
public class FoodService {
	
	private FoodRepository foodRepo;
	
	@Autowired
	public FoodService(FoodRepository foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}
	
	public Food findById(int food) {
		return foodRepo.findById(food).orElseThrow();
	}
	
	public List<Food> findAll() {
		return foodRepo.findAll();
	}
	
	public void create(Food food) {
		foodRepo.save(food);
	}
	
	public void update(@RequestBody Food food) {
		foodRepo.save(food);
	}
	
	public void deleteById(int id) {
		foodRepo.deleteById(id);
	}
	
	public Food findByName(String name) {
		return foodRepo.findByName(name).orElseThrow();
	}
	
	public List<Food> searchByName(String searchString) {
		String searchTerm = "%" + searchString + "%";
		return foodRepo.findByNameLikeIgnoreCase(searchTerm);
	}
	
	public List<Food> findAffordable(int inGameWealth) {
		return foodRepo.findByPriceLessThanEqualOrderByPriceAsc(inGameWealth);
	}
	
	public List<Food> findUnaffordable(int inGameWealth) {
		return foodRepo.findByPriceGreaterThanOrderByPriceAsc(inGameWealth);
	}
}
