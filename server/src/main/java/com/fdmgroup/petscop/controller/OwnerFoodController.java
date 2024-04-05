package com.fdmgroup.petscop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("find/owner/{ownerId}")
	public List<OwnerFood> findByOwner(@PathVariable int ownerId) {
		return ownerFoodService.findByOwner(ownerId);
	}
	
	@GetMapping
	public List<OwnerFood> findAll() {
		return ownerFoodService.findAll();
	}
	
	@PostMapping("create")
	public void create(@RequestBody OwnerFood ownerFood) {
		ownerFoodService.create(ownerFood);
	}
	
	@PostMapping("update")
	public void update(@RequestBody OwnerFood ownerFood) {
		ownerFoodService.update(ownerFood);
	}
	
	@PutMapping("delete/key/{ownerId}/{foodId}")
	public void deleteByKey(@PathVariable int ownerId, @PathVariable int foodId) {
		ownerFoodService.deleteByKey(ownerId, foodId);
	}
}