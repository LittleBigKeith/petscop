package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.petscop.dal.PetRepository;
import com.fdmgroup.petscop.model.Pet;

@Service
public class PetService {
	
	private PetRepository petRepo;
	
	@Autowired
	public PetService(PetRepository petRepo) {
		super();
		this.petRepo = petRepo;
	}
	
	public Pet findById(int id) {
		return petRepo.findById(id).orElseThrow();
	}
	
	public List<Pet> findAll() {
		return petRepo.findAll();
	}
	
	public void create(Pet pet) {
		petRepo.save(pet);
	}
	
	public void update(@RequestBody Pet pet) {
		petRepo.save(pet);
	}
	
	public void deleteById(int id) {
		petRepo.deleteById(id);
	}
	
	public Pet findByDefaultName(String defaultName) {
		return petRepo.findByDefaultName(defaultName).orElseThrow();
	}
	
	public List<Pet> searchByDefaultName(String searchString) {
		String searchTerm = "%" + searchString + "%";
		return petRepo.findByDefaultNameLikeIgnoreCase(searchTerm);
	}
	
	public List<Pet> findByGender(Pet.Gender gender) {
		return petRepo.findByGender(gender);
	}
	
	public List<Pet> findAffordable(int inGameWealth) {
		return petRepo.findByPriceLessThanEqualOrderByPriceAsc(inGameWealth);
	}
	
	public List<Pet> findUnaffordable(int inGameWealth) {
		return petRepo.findByPriceGreaterThanOrderByPriceAsc(inGameWealth);
	}
}
