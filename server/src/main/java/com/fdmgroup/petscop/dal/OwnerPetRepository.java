package com.fdmgroup.petscop.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.model.Pet;

@Repository
public interface OwnerPetRepository extends JpaRepository<OwnerPet, Integer>{
	List<OwnerPet> findByOwner(Owner owner);
	List<OwnerPet> findByPet(Pet pet);	
	OwnerPet findByGivenName(String givenName);
	List<OwnerPet> findByGivenNameLikeIgnoreCase(String searchTerm);
}
