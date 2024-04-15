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

import com.fdmgroup.petscop.controller.OwnerFoodController;
import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.service.OwnerFoodService;

@ExtendWith(MockitoExtension.class)
public class OwnerFoodControllerTest {

	OwnerFoodController ownerFoodController;
    
    @Mock
    OwnerFoodService ownerFoodServiceMock;
    
    @Mock
    OwnerFood ownerFoodMock;
    
    @BeforeEach
    void init () {
    	ownerFoodController = new OwnerFoodController(ownerFoodServiceMock);
    	assertNotNull(ownerFoodServiceMock);
    	assertNotNull(ownerFoodMock);
    }
   
    @Test
    void TestIf_OwnerFoodControllerCanFindByKey() {
    	String ownerName = "Owner";
    	String foodName = "Food";
        when(ownerFoodServiceMock.findByKey(ownerName, foodName)).thenReturn(ownerFoodMock);
        OwnerFood retrievedOwnerFood = ownerFoodController.findByKey(ownerName, foodName);
    	verify(ownerFoodServiceMock, times(1)).findByKey(ownerName, foodName);
    	assertEquals(retrievedOwnerFood, ownerFoodMock);
    }
    
    @Test
    void TestIf_OwnerFoodControllerCanFindByOwner() {
    	int numberOfOwnerFoods = 42;
    	Owner owner = new Owner();
    	List<OwnerFood> ownerFoodList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerFoods; i++) {
    		ownerFoodList.add(ownerFoodMock);
    	}
    	when(ownerFoodServiceMock.findByOwner(owner.getUsername())).thenReturn(ownerFoodList);
    	List<OwnerFood> retrievedOwnerFoodList = ownerFoodController.findByOwner(owner.getUsername());
    	verify(ownerFoodServiceMock, times(1)).findByOwner(owner.getUsername());
    	assertEquals(retrievedOwnerFoodList, ownerFoodList);
    }
    
    @Test
    void TestIf_OwnerFoodControllerCanFindAll() {
    	int numberOfOwnerFoods = 42;
    	List<OwnerFood> ownerFoodList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerFoods; i++) {
    		ownerFoodList.add(ownerFoodMock);
    	}
    	when(ownerFoodServiceMock.findAll()).thenReturn(ownerFoodList);
    	List<OwnerFood> retrievedOwnerFoods = ownerFoodController.findAll();
    	verify(ownerFoodServiceMock, times(1)).findAll();
		assertEquals(retrievedOwnerFoods, ownerFoodList);
    }
    
    
    @Test
    void TestIf_ownerFoodControllerCanCreateOrUpdate() {
    	ownerFoodController.create(ownerFoodMock);
    	verify(ownerFoodServiceMock, times(1)).createOrUpdate(ownerFoodMock);
    }
    
    @Test
    void TestIf_ownerFoodControllerCanDeleteByKey() {
    	Owner owner = new Owner();
        Food food = new Food(); 
    	ownerFoodController.deleteByKey(owner.getId(), food.getId());
    	verify(ownerFoodServiceMock, times(1)).deleteByKey(owner.getId(), food.getId());
    }
   
}
