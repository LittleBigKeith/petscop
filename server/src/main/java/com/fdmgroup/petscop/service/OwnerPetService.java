package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.petscop.dal.OwnerPetRepository;
import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.OwnerPet;

@Service
public class OwnerPetService {

	private OwnerPetRepository ownerPetRepo;
	private OwnerRepository ownerRepo;

	@Autowired
	public OwnerPetService(OwnerPetRepository ownerPetRepo, OwnerRepository ownerRepo) {
		super();
		this.ownerPetRepo = ownerPetRepo;
		this.ownerRepo = ownerRepo;
	}

	public OwnerPet findById(int id) {
		return ownerPetRepo.findById(id).get();
	}
	
	public List<OwnerPet> findByOwnerId(int ownerId) {
		return ownerPetRepo.findByOwner(ownerRepo.findById(ownerId).get());
	}
	
	public List<OwnerPet> findAll() {
		return ownerPetRepo.findAll();
	}
	
	public void create(OwnerPet ownerPet) {
		ownerPetRepo.save(ownerPet);
	}
	
	public void update(@RequestBody OwnerPet ownerPet) {
		ownerPetRepo.save(ownerPet);
	}
	
	public void deleteById(int id) {
		ownerPetRepo.deleteById(id);
	}
	
	public List<OwnerPet> searchByGivenName(String searchString) {
		String searchTerm = "%" + searchString + "%";
		return ownerPetRepo.findByGivenNameLikeIgnoreCase(searchTerm);
	}
}
