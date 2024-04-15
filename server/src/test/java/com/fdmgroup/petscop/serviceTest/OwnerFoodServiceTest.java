package com.fdmgroup.petscop.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
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
import com.fdmgroup.petscop.dal.OwnerFoodRepository;
import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.model.OwnerFoodEmbeddedKey;
import com.fdmgroup.petscop.service.OwnerFoodService;

@ExtendWith(MockitoExtension.class)
public class OwnerFoodServiceTest {

	OwnerFoodService ownerFoodService;
    
    @Mock
    OwnerFoodRepository ownerFoodRepoMock;
    
    @Mock
    OwnerRepository ownerRepoMock;
    
    @Mock
    FoodRepository foodRepoMock;
    
    @Mock
    OwnerFood ownerFoodMock;
    
    @BeforeEach
    void init () {
    	ownerFoodService = new OwnerFoodService(ownerFoodRepoMock, ownerRepoMock, foodRepoMock);
    	assertNotNull(ownerFoodRepoMock);
    	assertNotNull(ownerRepoMock);
    	assertNotNull(foodRepoMock);
    	assertNotNull(ownerFoodMock);
    }
   
    @Test
    void TestIf_OwnerFoodServiceCanFindByKey() {
    	Owner owner = new Owner();
        Food food = new Food(); 
        OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(owner, food);
        when(ownerFoodRepoMock.findByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey)).thenReturn(Optional.of(ownerFoodMock));
        doReturn(Optional.of(owner)).when(ownerRepoMock).findByUsername(owner.getUsername());
        doReturn(Optional.of(food)).when(foodRepoMock).findByName(food.getName());
        OwnerFood retrievedOwnerFood = ownerFoodService.findByKey(owner.getUsername(), food.getName());
    	verify(ownerFoodRepoMock, times(1)).findByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey);
    	assertEquals(retrievedOwnerFood, ownerFoodMock);
    }
    
    @Test
    void TestIf_OwnerFoodServiceCanFindByOwner() {
    	int numberOfOwnerFoods = 42;
    	Owner owner = new Owner();
    	List<OwnerFood> ownerFoodList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerFoods; i++) {
    		ownerFoodList.add(ownerFoodMock);
    	}
    	when(ownerFoodRepoMock.findByOwnerFoodEmbeddedKeyOwner(owner)).thenReturn(ownerFoodList);
    	doReturn(Optional.of(owner)).when(ownerRepoMock).findById(owner.getId());
    	List<OwnerFood> retrievedOwnerFoodList = ownerFoodService.findByOwner(owner.getUsername());
    	verify(ownerFoodRepoMock, times(1)).findByOwnerFoodEmbeddedKeyOwner(owner);
    	assertEquals(retrievedOwnerFoodList, ownerFoodList);
    }
    
    @Test
    void TestIf_OwnerFoodServiceCanFindAll() {
    	int numberOfOwnerFoods = 42;
    	List<OwnerFood> ownerFoodList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerFoods; i++) {
    		ownerFoodList.add(ownerFoodMock);
    	}
    	when(ownerFoodRepoMock.findAll()).thenReturn(ownerFoodList);
    	List<OwnerFood> retrievedOwnerFoods = ownerFoodService.findAll();
    	verify(ownerFoodRepoMock, times(1)).findAll();
		assertEquals(retrievedOwnerFoods, ownerFoodList);
    }
    
    
    @Test
    void TestIf_ownerFoodServiceCanCreate() {
    	ownerFoodService.createOrUpdate(ownerFoodMock);
    	verify(ownerFoodRepoMock, times(1)).save(ownerFoodMock);
    }

    @Test
    void TestIf_ownerFoodServiceCanDeleteByKey() {
    	Owner owner = new Owner();
        Food food = new Food(); 
        OwnerFoodEmbeddedKey ownerFoodEmbeddedKey = new OwnerFoodEmbeddedKey(owner, food);
        doReturn(Optional.of(owner)).when(ownerRepoMock).findById(owner.getId());
        doReturn(Optional.of(food)).when(foodRepoMock).findById(food.getId());
    	ownerFoodService.deleteByKey(owner.getId(), food.getId());
    	verify(ownerFoodRepoMock, times(1)).deleteByOwnerFoodEmbeddedKey(ownerFoodEmbeddedKey);
    }
   
}

