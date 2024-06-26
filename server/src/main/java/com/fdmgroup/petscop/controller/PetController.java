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

import com.fdmgroup.petscop.model.Pet;
import com.fdmgroup.petscop.service.PetService;

@RestController
@RequestMapping("pet")
@CrossOrigin("http://localhost:5173")
public class PetController {
	
	private PetService petService;

	@Autowired
	public PetController(PetService petService) {
		super();
		this.petService = petService;
	}
	
	@GetMapping("find/id/{id}")
	public Pet findById(@PathVariable int id) {
		return petService.findById(id);
	}
	
	@GetMapping
	public List<Pet> findAll() {
		return petService.findAll();
	}
	
	@PostMapping("update")
	public void create(@RequestBody Pet pet) {
		petService.createOrUpdate(pet);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		petService.deleteById(id);
	}
	
	@GetMapping("find/name/{name}")
	public Pet findByDefaultName(@PathVariable String name) {
		return petService.findByDefaultName(name);
	}
	
	@GetMapping("search/{searchString}")
	public List<Pet> searchByName(@PathVariable String searchString) {
		return petService.searchByDefaultName(searchString);
	}
	
	@GetMapping("find/affordable/{gold}")
	public List<Pet> findAffordable(@PathVariable int gold) {
		return petService.findAffordable(gold);
	}
	
	@GetMapping("find/unaffordable/{gold}")
	public List<Pet> findUnaffordable(@PathVariable int gold) {
		return petService.findUnaffordable(gold);
	}
}
