package com.fdmgroup.petscop.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.model.OwnerFoodEmbeddedKey;

import jakarta.transaction.Transactional;

@Repository
public interface OwnerFoodRepository extends JpaRepository<OwnerFood, Integer>{
	Optional<OwnerFood> findByOwnerFoodEmbeddedKey(OwnerFoodEmbeddedKey ownerFoodEmbeddedKey);
	List<OwnerFood> findByOwnerFoodEmbeddedKeyOwner(Owner owner);
	@Transactional
	Optional<OwnerFood> deleteByOwnerFoodEmbeddedKey(OwnerFoodEmbeddedKey ownerFoodEmbeddedKey);
}
