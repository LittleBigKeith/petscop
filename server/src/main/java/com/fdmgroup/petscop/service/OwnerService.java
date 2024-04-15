package com.fdmgroup.petscop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.exceptions.ConflictException;
import com.fdmgroup.petscop.exceptions.UnprocessableEntityException;
import com.fdmgroup.petscop.model.Owner;

@Service
public class OwnerService {
	
	private OwnerRepository ownerRepo;
	// For password hashing
	private PasswordEncoder encoder;
	
	@Autowired
	public OwnerService(OwnerRepository ownerRepo, PasswordEncoder encoder) {
		super();
		this.ownerRepo = ownerRepo;
		this.encoder = encoder;
	}
	
	public Owner findById(int id) {
		return ownerRepo.findById(id).orElseThrow();
	}
	
	public List<Owner> findAll() {
		return ownerRepo.findAll();
	}
	
	public void create(Owner owner) {
		if (!ownerRepo.findByUsername(owner.getUsername()).isEmpty()) {
			throw new ConflictException("User with this username already exists");
		} else if (owner.getUsername().isBlank()) {
			throw new UnprocessableEntityException("Username must have non-blank characters");
	 	} else if (owner.getPassword().length() < 8) {
			throw new UnprocessableEntityException("Password must have at least 8 characters");
		}
		String hashedPassword = encoder.encode(owner.getPassword());
		owner.setPassword(hashedPassword);
		ownerRepo.save(owner);
	}
	
	public void update(@RequestBody Owner owner) {
		if (owner.getGold() < 0) {
			throw new UnprocessableEntityException("Invalid operation");
		}
		ownerRepo.save(owner);
	}
	
	public void deleteById(int id) {
		ownerRepo.deleteById(id);
	}
	
	public Owner findByUsername(String username) {
		Owner foundOwner = ownerRepo.findByUsername(username).orElseThrow(
			() -> new UnprocessableEntityException("Invalid username or password")
		);
 
		return foundOwner;
	}
	
	public List<Owner> findByRole(Owner.Role role) {
		return ownerRepo.findByRole(role);
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
}
