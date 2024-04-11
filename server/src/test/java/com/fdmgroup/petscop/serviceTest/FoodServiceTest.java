package com.fdmgroup.petscop.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.petscop.dal.FoodRepository;
import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.service.FoodService;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    FoodService foodService;
    
    @Mock
    FoodRepository foodRepoMock;

    @Mock
    Food foodMock;
    
    @BeforeEach
    void init () {
    	foodService = new FoodService(foodRepoMock);
    	assertNotNull(foodRepoMock);
    	assertNotNull(foodMock);
    }
   
    @Test
    void TestIf_FoodServiceCanFindById() {
    	int id = 42;
    	when(foodRepoMock.findById(id)).thenReturn(Optional.of(foodMock));
    	Food retrievedFood = foodService.findById(id);
    	verify(foodRepoMock, times(1)).findById(id);
    	assertEquals(retrievedFood, foodMock);
    }
    
    @Test
    void TestIf_FoodServiceCanFindAll() {
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodRepoMock.findAll()).thenReturn(foodList);
    	List<Food> retrievedFoods = foodService.findAll();
    	verify(foodRepoMock, times(1)).findAll();
		assertEquals(retrievedFoods, foodList);
    }
    
    @Test
    void TestIf_FoodServiceCanCreate() {
    	foodService.createOrUpdate(foodMock);
    	verify(foodRepoMock, times(1)).save(foodMock);
    }
    
    @Test
    void TestIf_FoodServiceCanDeleteById() {
    	int id = 42;
    	foodService.deleteById(id);
    	verify(foodRepoMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_FoodServiceCanFindByName() {
    	String name = "abc";
    	when(foodRepoMock.findByName(name)).thenReturn(Optional.of(foodMock));
    	Food retrievedFood = foodService.findByName(name);
    	verify(foodRepoMock, times(1)).findByName(name);
    	assertEquals(retrievedFood, foodMock);
    }
  
    @Test
    void TestIf_FoodServiceCanSearchByName() {
    	int numberOfResults = 42;
    	String searchTerm = "abc";
    	List<Food> resultList = new ArrayList<>();
    	for (int i = 0; i < numberOfResults; i++) {
    		resultList.add(foodMock);
    	}
    	when(foodRepoMock.findByNameLikeIgnoreCase("%" + searchTerm + "%")).thenReturn(resultList);
    	List<Food> retrievedResults = foodService.searchByName(searchTerm);
    	verify(foodRepoMock, times(1)).findByNameLikeIgnoreCase("%" + searchTerm + "%");
    	assertEquals(retrievedResults, resultList);
    }
    
    @Test
    void TestIf_FoodServiceCanFindAffordable() {
    	int gold = 42;
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodRepoMock.findByPriceLessThanEqualOrderByPriceAsc(gold)).thenReturn(foodList);
    	List<Food> retrievedFoods = foodService.findAffordable(gold);
    	verify(foodRepoMock, times(1)).findByPriceLessThanEqualOrderByPriceAsc(gold);
    	assertEquals(retrievedFoods, foodList);
    }
    
    @Test
    void TestIf_FoodServiceCanFindUnaffordable() {
    	int gold = 42;
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodRepoMock.findByPriceGreaterThanOrderByPriceAsc(numberOfFoods)).thenReturn(foodList);
    	List<Food> retrievedFoods = foodService.findUnaffordable(gold);
    	verify(foodRepoMock, times(1)).findByPriceGreaterThanOrderByPriceAsc(gold);
    	assertEquals(retrievedFoods, foodList);
    }
}