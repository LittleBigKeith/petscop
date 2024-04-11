package com.fdmgroup.petscop.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.petscop.controller.FoodController;
import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.service.FoodService;

@ExtendWith(MockitoExtension.class)
public class FoodControllerTest {

    FoodController foodController;
    
    @Mock
    FoodService foodServiceMock;
    
    @Mock
    Food foodMock;
    
    @BeforeEach
    void init () {
    	foodController = new FoodController(foodServiceMock);
    	assertNotNull(foodServiceMock);
    }
   
    @Test
    void TestIf_FoodControllerCanFindById() {
    	int id = 42;
    	when(foodServiceMock.findById(id)).thenReturn(foodMock);
    	Food retrievedFood = foodController.findById(id);
    	verify(foodServiceMock, times(1)).findById(id);
    	assertEquals(retrievedFood, foodMock);
    }
    
    @Test
    void TestIf_FoodControllerCanFindAll() {
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodServiceMock.findAll()).thenReturn(foodList);
    	List<Food> retrievedFoods = foodController.findAll();
    	verify(foodServiceMock, times(1)).findAll();
		assertEquals(retrievedFoods, foodList);
    }
    
    @Test
    void TestIf_FoodControllerCanCreateOrUpdate() {
    	foodController.create(foodMock);
    	verify(foodServiceMock, times(1)).createOrUpdate(foodMock);
    }
   
    @Test
    void TestIf_FoodControllerCanDeleteById() {
    	int id = 42;
    	foodController.delete(id);
    	verify(foodServiceMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_FoodControllerCanFindByName() {
    	String name = "abc";
    	when(foodServiceMock.findByName(name)).thenReturn(foodMock);
    	Food retrievedFood = foodController.findByName(name);
    	verify(foodServiceMock, times(1)).findByName(name);
    	assertEquals(retrievedFood, foodMock);
    }
  
    @Test
    void TestIf_FoodControllerCanSearchByName() {
    	int numberOfResults = 42;
    	String searchTerm = "abc";
    	List<Food> resultList = new ArrayList<>();
    	for (int i = 0; i < numberOfResults; i++) {
    		resultList.add(foodMock);
    	}
    	when(foodServiceMock.searchByName(searchTerm)).thenReturn(resultList);
    	List<Food> retrievedResults = foodController.searchByName(searchTerm);
    	verify(foodServiceMock, times(1)).searchByName(searchTerm);
    	assertEquals(retrievedResults, resultList);
    }
    
    @Test
    void TestIf_FoodControllerCanFindAffordable() {
    	int gold = 42;
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodServiceMock.findAffordable(gold)).thenReturn(foodList);
    	List<Food> retrievedFoods = foodController.findAffordable(gold);
    	verify(foodServiceMock, times(1)).findAffordable(gold);
    	assertEquals(retrievedFoods, foodList);
    }
    
    @Test
    void TestIf_FoodControllerCanFindUnaffordable() {
    	int gold = 42;
    	int numberOfFoods = 42;
    	List<Food> foodList = new ArrayList<>();
    	for (int i = 0; i < numberOfFoods; i++) {
    		foodList.add(foodMock);
    	}
    	when(foodServiceMock.findUnaffordable(numberOfFoods)).thenReturn(foodList);
    	List<Food> retrievedFoods = foodController.findUnaffordable(gold);
    	verify(foodServiceMock, times(1)).findUnaffordable(gold);
    	assertEquals(retrievedFoods, foodList);
    }
}
