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

import com.fdmgroup.petscop.dal.OwnerPetRepository;
import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.service.OwnerPetService;

@ExtendWith(MockitoExtension.class)
public class OwnerPetServiceTest {

	OwnerPetService ownerPetService;
    
    @Mock
    OwnerPetRepository ownerPetRepoMock;
    
    @Mock
    OwnerRepository ownerRepoMock;

    @Mock
    OwnerPet ownerPetMock;
    
    
    @BeforeEach
    void init () {
    	ownerPetService = new OwnerPetService(ownerPetRepoMock, ownerRepoMock);
    	assertNotNull(ownerPetRepoMock);
    	assertNotNull(ownerPetMock);
    }
   
    @Test
    void TestIf_OwnerPetServiceCanFindById() {
    	int id = 42;
    	when(ownerPetRepoMock.findById(id)).thenReturn(Optional.of(ownerPetMock));
    	OwnerPet retrievedOwnerPet = ownerPetService.findById(id);
    	verify(ownerPetRepoMock, times(1)).findById(id);
    	assertEquals(retrievedOwnerPet, ownerPetMock);
    }
    
    @Test
    void TestIf_OwnerPetServiceCanFindByOwner() {
    	int numberOfOwnerPets = 42;
    	Owner owner = new Owner();
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetRepoMock.findByOwner(owner)).thenReturn(ownerPetList);
    	doReturn(Optional.of(owner)).when(ownerRepoMock).findById(owner.getId());
    	List<OwnerPet> retrievedOwnerPetList = ownerPetService.findByOwnerId(owner.getId());
    	verify(ownerPetRepoMock, times(1)).findByOwner(owner);
    	assertEquals(retrievedOwnerPetList, ownerPetList);
    }
    	
    @Test
    void TestIf_OwnerPetServiceCanFindAll() {
    	int numberOfOwnerPets = 42;
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetRepoMock.findAll()).thenReturn(ownerPetList);
    	List<OwnerPet> retrievedOwnerPets = ownerPetService.findAll();
    	verify(ownerPetRepoMock, times(1)).findAll();
		assertEquals(retrievedOwnerPets, ownerPetList);
    }
    
    @Test
    void TestIf_OwnerPetServiceCanCreate() {
    	ownerPetService.create(ownerPetMock);
    	verify(ownerPetRepoMock, times(1)).save(ownerPetMock);
    }
    
    @Test
    void TestIf_OwnerPetServiceCanUpdate() {
    	ownerPetService.update(ownerPetMock);
    	verify(ownerPetRepoMock, times(1)).save(ownerPetMock);
    }
    
    @Test
    void TestIf_OwnerPetServiceCanDeleteById() {
    	int id = 42;
    	ownerPetService.deleteById(id);
    	verify(ownerPetRepoMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_OwnerPetServiceCanSearchByGivenName() {
    	int numberOfOwnerPets = 42;
    	String searchTerm = "Given Name";
    	List<OwnerPet> ownerPetList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwnerPets; i++) {
    		ownerPetList.add(ownerPetMock);
    	}
    	when(ownerPetRepoMock.findByGivenNameLikeIgnoreCase("%" + searchTerm + "%")).thenReturn(ownerPetList);
    	List<OwnerPet> retrievedOwnerPets = ownerPetService.searchByGivenName(searchTerm);
    	verify(ownerPetRepoMock, times(1)).findByGivenNameLikeIgnoreCase("%" + searchTerm+ "%");
    	assertEquals(retrievedOwnerPets, ownerPetList);
    }
    
}

