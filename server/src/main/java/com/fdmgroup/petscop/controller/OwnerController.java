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

import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.service.OwnerService;

@RestController
@RequestMapping("owner")
public class OwnerController {
	
	private OwnerService ownerService;

	@Autowired
	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}
	
	@GetMapping("find/id/{id}")
	public Owner findById(@PathVariable int id) {
		return ownerService.findById(id);
	}
	
	@GetMapping
	public List<Owner> findAll() {
		return ownerService.findAll();
	}
	
	@PostMapping("create")
	public void create(@RequestBody Owner owner) {
		ownerService.create(owner);
	}
	
	@PostMapping("update")
	public void update(@RequestBody Owner owner) {
		ownerService.update(owner);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		ownerService.deleteById(id);
	}
	
	@GetMapping("find/name/{name}")
	public Owner findByName(@PathVariable String name) {
		return ownerService.findByUsername(name);
	}
	
	@GetMapping("search/{searchString}")
	public List<Owner> searchByName(@PathVariable String searchString) {
		return ownerService.searchByUsername(searchString);
	}
	
	@GetMapping("sort/name/asc")
	public List<Owner> sortByNameAtoZ() {
		return ownerService.sortByUsernameAtoZ();
	}
	
	@GetMapping("sort/name/desc")
	public List<Owner> sortByNameZtoA() {
		return ownerService.sortByUsernameZtoA();
	}
	
	@GetMapping("sort/cake date/asc")
	public List<Owner> sortByCakeDateOldToNew() {
		return ownerService.sortByCakeDateOldToNew();
	}
	
	@GetMapping("sort/cake date/desc")
	public List<Owner> sortByCakeDateNewToOld() {
		return ownerService.sortByCakeDateNewToOld();
	}
	
	@GetMapping("sort/wealth/asc")
	public List<Owner> sortByWealthLowToHigh() {
		return ownerService.sortByWealthLowToHigh();
	}
	
	@GetMapping("sort/wealth/desc")
	public List<Owner> sortByWealthHighToLow() {
		return ownerService.sortByWealthHighToLow();
	}
	
	@GetMapping("sort/exp/asc")
	public List<Owner> sortByExperienceLowToHigh() {
		return ownerService.sortByWealthLowToHigh();
	}
	
	@GetMapping("sort/exp/desc")
	public List<Owner> sortByExperienceHighToLow() {
		return ownerService.sortByWealthHighToLow();
	}
}

