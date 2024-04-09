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

import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.service.FoodService;

@RestController
@RequestMapping("food")
public class FoodController {
	
	private FoodService foodService;

	@Autowired
	public FoodController(FoodService foodService) {
		super();
		this.foodService = foodService;
	}
	
	@GetMapping("find/id/{id}")
	public Food findById(@PathVariable int id) {
		return foodService.findById(id);
	}
	
	@GetMapping
	public List<Food> findAll() {
		return foodService.findAll();
	}
	
	@PostMapping("create")
	public void create(@RequestBody Food food) {
		foodService.create(food);
	}
	
	@PostMapping("update")
	public void update(@RequestBody Food food) {
		foodService.update(food);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		foodService.deleteById(id);
	}
	
	@GetMapping("find/name/{name}")
	public Food findByName(@PathVariable String name) {
		return foodService.findByName(name);
	}
	
	@GetMapping("search/{searchString}")
	public List<Food> searchByName(@PathVariable String searchString) {
		return foodService.searchByName(searchString);
	}
	
	@GetMapping("find/affordable/{Gold}")
	public List<Food> findAffordable(@PathVariable int Gold) {
		return foodService.findAffordable(Gold);
	}
	
	@GetMapping("find/unaffordable/{Gold}")
	public List<Food> findUnaffordable(@PathVariable int Gold) {
		return foodService.findUnaffordable(Gold);
	}
}
