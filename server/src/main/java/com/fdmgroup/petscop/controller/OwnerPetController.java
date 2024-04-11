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

import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.service.OwnerPetService;

@RestController
@RequestMapping("ownerpet")
@CrossOrigin("http://localhost:5173")
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
	
	@GetMapping("find/owner/{username}")
	public List<OwnerPet> findByOwner(@PathVariable String username) {
		return ownerPetService.findByOwnerUsername(username);
	}
	
	@GetMapping
	public List<OwnerPet> findAll() {
		return ownerPetService.findAll();
	}
	
	@PostMapping("update")
	public void createOrUpdate(@RequestBody OwnerPet ownerPet) {
		ownerPetService.createOrUpdate(ownerPet);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		ownerPetService.deleteById(id);
	}
	
	@GetMapping("search/{searchString}")
	public List<OwnerPet> searchByName(@PathVariable String searchString) {
		return ownerPetService.searchByGivenName(searchString);
	}
}