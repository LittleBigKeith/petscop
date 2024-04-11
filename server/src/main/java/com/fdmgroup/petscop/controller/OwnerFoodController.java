package com.fdmgroup.petscop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.service.OwnerFoodService;

@RestController
@RequestMapping("ownerfood")
@CrossOrigin("http://localhost:5173")
public class OwnerFoodController {
	
	private OwnerFoodService ownerFoodService;

	@Autowired
	public OwnerFoodController(OwnerFoodService ownerFoodService) {
		super();
		this.ownerFoodService = ownerFoodService;
	}
	
	@GetMapping("find/key/{ownerId}/{foodId}")
	public OwnerFood findByKey(@PathVariable int ownerId, @PathVariable int foodId) {
		return ownerFoodService.findByKey(ownerId, foodId);
	}
	
	@GetMapping("find/owner/{username}")
	public List<OwnerFood> findByOwner(@PathVariable String username) {
		return ownerFoodService.findByOwner(username);
	}
	
	@GetMapping
	public List<OwnerFood> findAll() {
		return ownerFoodService.findAll();
	}
	
	@PostMapping("update")
	public void create(@RequestBody OwnerFood ownerFood) {
		ownerFoodService.createOrUpdate(ownerFood);
	}

	@PutMapping("delete/key/{ownerId}/{foodId}")
	public void deleteByKey(@PathVariable int ownerId, @PathVariable int foodId) {
		ownerFoodService.deleteByKey(ownerId, foodId);
	}
}