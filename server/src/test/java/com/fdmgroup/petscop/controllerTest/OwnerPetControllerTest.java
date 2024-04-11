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

import com.fdmgroup.petscop.controller.OwnerPetController;
import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.service.OwnerPetService;

@ExtendWith(MockitoExtension.class)
public class OwnerPetControllerTest {

	OwnerPetController ownerPetController;
    
    @Mock
    OwnerPetService ownerPetServiceMock;
    
    @Mock
    OwnerRepository ownerRepoMock;

    @Mock
    OwnerPet ownerPetMock;
    
    
    @BeforeEach
    void init () {
    	ownerPetController = new OwnerPetController(ownerPetServiceMock);
    	assertNotNull(ownerPetServiceMock);
    	assertNotNull(ownerPetMock);
    }
   
    @Test
    void TestIf_OwnerPetControllerCanFindById() {
    	int id = 42;
    	when(ownerPetServiceMock.findById(id)).thenReturn(ownerPetMock);
    	OwnerPet retrievedOwnerPet = ownerPetController.findById(id);
    	verify(ownerPetServiceMock, times(1)).findById(id);
    	assertEquals(retrievedOwnerPet, ownerPetMock);
    }
    
    @Test
    void TestIf_OwnerPetControllerCanFindByOwner() {
    	int numberOfOwnerPets = 42;
    	Owner owner = new Owner();
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetServiceMock.findByOwnerUsername(owner.getUsername())).thenReturn(ownerPetList);
    	List<OwnerPet> retrievedOwnerPetList = ownerPetController.findByOwner(owner.getUsername());
    	verify(ownerPetServiceMock, times(1)).findByOwnerUsername(owner.getUsername());
    	assertEquals(retrievedOwnerPetList, ownerPetList);
    }
    	
    @Test
    void TestIf_OwnerPetControllerCanFindAll() {
    	int numberOfOwnerPets = 42;
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetServiceMock.findAll()).thenReturn(ownerPetList);
    	List<OwnerPet> retrievedOwnerPets = ownerPetController.findAll();
    	verify(ownerPetServiceMock, times(1)).findAll();
		assertEquals(retrievedOwnerPets, ownerPetList);
    }
    
    @Test
    void TestIf_OwnerPetControllerCanCreateOrUpdate() {
    	ownerPetController.createOrUpdate(ownerPetMock);
    	verify(ownerPetServiceMock, times(1)).createOrUpdate(ownerPetMock);
    }
    
    @Test
    void TestIf_OwnerPetControllerCanDeleteById() {
    	int id = 42;
    	ownerPetController.delete(id);
    	verify(ownerPetServiceMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_OwnerPetControllerCanSearchByGivenName() {
    	int numberOfOwnerPets = 42;
    	String searchTerm = "Given Name";
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetServiceMock.searchByGivenName(searchTerm)).thenReturn(ownerPetList);
    	List<OwnerPet> retrievedOwnerPets = ownerPetController.searchByName(searchTerm);
    	verify(ownerPetServiceMock, times(1)).searchByGivenName(searchTerm);
    	assertEquals(retrievedOwnerPets, ownerPetList);
    }
    
}
