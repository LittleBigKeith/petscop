package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Owner;

@Service
public class OwnerService {
	
	private OwnerRepository ownerRepo;
	
	@Autowired
	public OwnerService(OwnerRepository ownerRepo) {
		super();
		this.ownerRepo = ownerRepo;
	}
	
	public Owner findById(int id) {
		return ownerRepo.findById(id).orElseThrow();
	}
	
	public List<Owner> findAll() {
		return ownerRepo.findAll();
	}
	
	public void create(Owner owner) {
		ownerRepo.save(owner);
	}
	
	public void update(@RequestBody Owner owner) {
		ownerRepo.save(owner);
	}
	
	public void deleteById(int id) {
		ownerRepo.deleteById(id);
	}
	
	public Owner findByUsername(String username) {
		return ownerRepo.findByUsername(username).orElseThrow();
	}
	
	public List<Owner> searchByUsername(String searchString) {
		String searchTerm = "%" + searchString + "%";
		return ownerRepo.findByUsernameLikeIgnoreCase(searchTerm);
	}
	
	public List<Owner> sortByUsernameAtoZ() {
		return ownerRepo.findAllByOrderByUsernameAsc();
	}
	
	public List<Owner> sortByUsernameZtoA() {
		return ownerRepo.findAllByOrderByUsernameDesc();
	}
	
	public List<Owner> sortByCakeDateOldToNew() {
		return ownerRepo.findAllByOrderByCakeDateAsc();
	}
	
	public List<Owner> sortByCakeDateNewToOld() {
		return ownerRepo.findAllByOrderByCakeDateDesc();
	}
	
	public List<Owner> sortByGoldLowToHigh() {
		return ownerRepo.findAllByOrderByGoldAsc();
	}
	
	public List<Owner> sortByGoldHighToLow() {
		return ownerRepo.findAllByOrderByGoldDesc();
	}
	
	public List<Owner> sortByExperienceLowToHigh() {
		return ownerRepo.findAllByOrderByExperienceAsc();
	}
	
	public List<Owner> sortByExperienceHighToLow() {
		return ownerRepo.findAllByOrderByExperienceDesc();
	}
}
