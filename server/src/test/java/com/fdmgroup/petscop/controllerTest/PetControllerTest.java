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

import com.fdmgroup.petscop.controller.PetController;
import com.fdmgroup.petscop.model.Pet;
import com.fdmgroup.petscop.service.PetService;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    PetController petController;
    
    @Mock
    PetService petServiceMock;

    @Mock
    Pet petMock;
    
    @BeforeEach
    void init () {
    	petController = new PetController(petServiceMock);
    	assertNotNull(petServiceMock);
    	assertNotNull(petMock);
    }
   
    @Test
    void TestIf_PetControllerCanFindById() {
    	int id = 42;
    	when(petServiceMock.findById(id)).thenReturn(petMock);
    	Pet retrievedPet = petController.findById(id);
    	verify(petServiceMock, times(1)).findById(id);
    	assertEquals(retrievedPet, petMock);
    }
    
    @Test
    void TestIf_PetControllerCanFindAll() {
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petServiceMock.findAll()).thenReturn(petList);
    	List<Pet> retrievedPets = petController.findAll();
    	verify(petServiceMock, times(1)).findAll();
		assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetControllerCanCreate() {
    	petController.create(petMock);
    	verify(petServiceMock, times(1)).create(petMock);
    }
    
    @Test
    void TestIf_PetControllerCanUpdate() {
    	petController.update(petMock);
    	verify(petServiceMock, times(1)).update(petMock);
    }
    
    @Test
    void TestIf_PetControllerCanDeleteById() {
    	int id = 42;
    	petController.delete(id);
    	verify(petServiceMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_PetControllerCanFindByDefaultName() {
    	String defaultName = "Default Name";
    	when(petServiceMock.findByDefaultName(defaultName)).thenReturn(petMock);
    	Pet retrievedPet = petController.findByDefaultName(defaultName);
    	verify(petServiceMock, times(1)).findByDefaultName(defaultName);
    	assertEquals(retrievedPet, petMock);
    }
    
    @Test
    void TestIf_PetControllerCanSearchByDefaultName() {
    	int numberOfPets = 42;
    	String searchTerm = "Default Name";
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petServiceMock.searchByDefaultName(searchTerm)).thenReturn(petList);
    	List<Pet> retrievedPets = petController.searchByName(searchTerm);
    	verify(petServiceMock, times(1)).searchByDefaultName(searchTerm);
    	assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetControllerCanFindByGender() {
    	int numberOfPets = 42;
    	Pet.Gender gender = Pet.Gender.F;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petServiceMock.findByGender(gender)).thenReturn(petList);
    	List<Pet> retrievedPets = petController.findByGender(gender);
    	verify(petServiceMock, times(1)).findByGender(gender);
    	assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetControllerCanFindAffordable() {
    	int gold = 42;
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petServiceMock.findAffordable(gold)).thenReturn(petList);
    	List<Pet> retrievedPets = petController.findAffordable(gold);
    	verify(petServiceMock, times(1)).findAffordable(gold);
    	assertEquals(retrievedPets, petList);
    }

    @Test
    void TestIf_PetControllerCanFindUnffordable() {
    	int gold = 42;
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petServiceMock.findUnaffordable(gold)).thenReturn(petList);
    	List<Pet> retrievedPets = petController.findUnaffordable(gold);
    	verify(petServiceMock, times(1)).findUnaffordable(gold);
    	assertEquals(retrievedPets, petList);
    }
}
