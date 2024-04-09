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

import com.fdmgroup.petscop.dal.PetRepository;
import com.fdmgroup.petscop.model.Pet;
import com.fdmgroup.petscop.service.PetService;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    PetService petService;
    
    @Mock
    PetRepository petRepoMock;

    @Mock
    Pet petMock;
    
    @BeforeEach
    void init () {
    	petService = new PetService(petRepoMock);
    	assertNotNull(petRepoMock);
    	assertNotNull(petMock);
    }
   
    @Test
    void TestIf_PetServiceCanFindById() {
    	int id = 42;
    	when(petRepoMock.findById(id)).thenReturn(Optional.of(petMock));
    	Pet retrievedPet = petService.findById(id);
    	verify(petRepoMock, times(1)).findById(id);
    	assertEquals(retrievedPet, petMock);
    }
    
    @Test
    void TestIf_PetServiceCanFindAll() {
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petRepoMock.findAll()).thenReturn(petList);
    	List<Pet> retrievedPets = petService.findAll();
    	verify(petRepoMock, times(1)).findAll();
		assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetServiceCanCreate() {
    	petService.create(petMock);
    	verify(petRepoMock, times(1)).save(petMock);
    }
    
    @Test
    void TestIf_PetServiceCanUpdate() {
    	petService.update(petMock);
    	verify(petRepoMock, times(1)).save(petMock);
    }
    
    @Test
    void TestIf_PetServiceCanDeleteById() {
    	int id = 42;
    	petService.deleteById(id);
    	verify(petRepoMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_PetServiceCanFindByDefaultName() {
    	String defaultName = "Default Name";
    	when(petRepoMock.findByDefaultName(defaultName)).thenReturn(Optional.of(petMock));
    	Pet retrievedPet = petService.findByDefaultName(defaultName);
    	verify(petRepoMock, times(1)).findByDefaultName(defaultName);
    	assertEquals(retrievedPet, petMock);
    }
    
    @Test
    void TestIf_PetServiceCanSearchByDefaultName() {
    	int numberOfPets = 42;
    	String searchTerm = "Default Name";
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petRepoMock.findByDefaultNameLikeIgnoreCase("%" + searchTerm + "%")).thenReturn(petList);
    	List<Pet> retrievedPets = petService.searchByDefaultName(searchTerm);
    	verify(petRepoMock, times(1)).findByDefaultNameLikeIgnoreCase("%" + searchTerm + "%");
    	assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetServiceCanFindByGender() {
    	int numberOfPets = 42;
    	Pet.Gender gender = Pet.Gender.F;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petRepoMock.findByGender(gender)).thenReturn(petList);
    	List<Pet> retrievedPets = petService.findByGender(gender);
    	verify(petRepoMock, times(1)).findByGender(gender);
    	assertEquals(retrievedPets, petList);
    }
    
    @Test
    void TestIf_PetServiceCanFindAffordable() {
    	int gold = 42;
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petRepoMock.findByPriceLessThanEqualOrderByPriceAsc(gold)).thenReturn(petList);
    	List<Pet> retrievedPets = petService.findAffordable(gold);
    	verify(petRepoMock, times(1)).findByPriceLessThanEqualOrderByPriceAsc(gold);
    	assertEquals(retrievedPets, petList);
    }

    @Test
    void TestIf_PetServiceCanFindUnffordable() {
    	int gold = 42;
    	int numberOfPets = 42;
    	List<Pet> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfPets; i++) {
    		petList.add(petMock);
    	}
    	when(petRepoMock.findByPriceGreaterThanOrderByPriceAsc(gold)).thenReturn(petList);
    	List<Pet> retrievedPets = petService.findUnaffordable(gold);
    	verify(petRepoMock, times(1)).findByPriceGreaterThanOrderByPriceAsc(gold);
    	assertEquals(retrievedPets, petList);
    }
}