package com.fdmgroup.petscop.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{
	Optional<Food> findByName(String name);
	List<Food> findByNameLikeIgnoreCase(String searchTerm);
	List<Food> findByPriceLessThanEqualOrderByPriceAsc(int price);
	List<Food> findByPriceGreaterThanOrderByPriceAsc(int price);
}
