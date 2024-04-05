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
import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.service.OwnerPetService;

@RestController
@RequestMapping("ownerpet")
public class OwnerPetController {
	
	private OwnerPetService ownerPetService;

	@Autowired
	public OwnerPetController(OwnerPetService ownerPetService) {
		super();
		this.ownerPetService = ownerPetService;
	}
	
	@GetMapping("find/id/{id}")
	public OwnerPet findById(@PathVariable int id) {
		return ownerPetService.findById(id);
	}
	
	@GetMapping("find/owner/{ownerId}")
	public List<OwnerPet> findByOwner(@PathVariable int ownerId) {
		return ownerPetService.findByOwner(ownerId);
	}
	
	@GetMapping
	public List<OwnerPet> findAll() {
		return ownerPetService.findAll();
	}
	
	@PostMapping("create")
	public void create(@RequestBody OwnerPet ownerPet) {
		ownerPetService.create(ownerPet);
	}
	
	@PostMapping("update")
	public void update(@RequestBody OwnerPet ownerPet) {
		ownerPetService.update(ownerPet);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		ownerPetService.deleteById(id);
	}
	
	@GetMapping("find/name/{name}")
	public OwnerPet findByName(@PathVariable String name) {
		return ownerPetService.findByGivenName(name);
	}
	
	@GetMapping("search/{searchString}")
	public List<OwnerPet> searchByName(@PathVariable String searchString) {
		return ownerPetService.searchByGivenName(searchString);
	}
}