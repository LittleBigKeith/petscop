package com.fdmgroup.petscop.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	Optional<Pet> findByDefaultName(String defaultName);
	List<Pet> findByDefaultNameLikeIgnoreCase(String searchTerm);
	List<Pet> findByGender(Pet.Gender gender);
	List<Pet> findByPriceLessThanEqualOrderByPriceAsc(int price);
	List<Pet> findByPriceGreaterThanOrderByPriceAsc(int price);
}
