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

import com.fdmgroup.petscop.controller.OwnerController;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.service.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

	OwnerController ownerController;
    
    @Mock
    OwnerService ownerServiceMock;

    @Mock
    Owner ownerMock;
    
    @BeforeEach
    void init () {
    	ownerController = new OwnerController(ownerServiceMock);
    	assertNotNull(ownerServiceMock);
    	assertNotNull(ownerMock);
    }
   
    @Test
    void TestIf_OwnerServiceCanFindById() {
    	int id = 42;
    	when(ownerServiceMock.findById(id)).thenReturn(ownerMock);
    	Owner retrievedOwner = ownerController.findById(id);
    	verify(ownerServiceMock, times(1)).findById(id);
    	assertEquals(retrievedOwner, ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanFindAll() {
    	int numberOfOwners = 42;
    	List<Owner> ownerList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwners; i++) {
    		ownerList.add(ownerMock);
    	}
    	when(ownerServiceMock.findAll()).thenReturn(ownerList);
    	List<Owner> retrievedOwners = ownerController.findAll();
    	verify(ownerServiceMock, times(1)).findAll();
		assertEquals(retrievedOwners, ownerList);
    }
    
    @Test
    void TestIf_OwnerServiceCanCreate() {
    	ownerController.create(ownerMock);
    	verify(ownerServiceMock, times(1)).create(ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanUpdate() {
    	ownerController.update(ownerMock);
    	verify(ownerServiceMock, times(1)).update(ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanDeleteById() {
    	int id = 42;
    	ownerController.delete(id);
    	verify(ownerServiceMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_OwnerServiceCanFindByUserName() {
    	String userName = "User name";
    	when(ownerServiceMock.findByUsername(userName)).thenReturn(ownerMock);
    	Owner retrievedOwner = ownerController.findByName(userName);
    	verify(ownerServiceMock, times(1)).findByUsername(userName);
    	assertEquals(retrievedOwner, ownerMock);
    }
    
    @Test
    void testIf_OwnerServiceCanSearchByUserName() {
    	int numberOfOwners = 42;
    	String searchTerm = "User name";
    	List<Owner> resultList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwners; i++) {
    		resultList.add(ownerMock);
    	}
    	when(ownerServiceMock.searchByUsername(searchTerm)).thenReturn(resultList);
    	List<Owner> retrievedOwners = ownerController.searchByName(searchTerm);
    	verify(ownerServiceMock, times(1)).searchByUsername(searchTerm);
    	assertEquals(retrievedOwners, resultList);
    }
    
    @Test
    void testIf_OwnerServiceCanSortByUsernameAtoZ() {
    	ownerController.sortByNameAtoZ();
    	verify(ownerServiceMock, times(1)).sortByUsernameAtoZ();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByUsernameZtoA() {
    	ownerController.sortByNameZtoA();
    	verify(ownerServiceMock, times(1)).sortByUsernameZtoA();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByCakeDateOldToNew() {
    	ownerController.sortByCakeDateOldToNew();
    	verify(ownerServiceMock, times(1)).sortByCakeDateOldToNew();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByCakeDateNewToOld() {
    	ownerController.sortByCakeDateNewToOld();
    	verify(ownerServiceMock, times(1)).sortByCakeDateNewToOld();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByGoldLowToHigh() {
    	ownerController.sortByGoldLowToHigh();
    	verify(ownerServiceMock, times(1)).sortByGoldLowToHigh();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByGoldHighToLow() {
    	ownerController.sortByGoldHighToLow();
    	verify(ownerServiceMock, times(1)).sortByGoldHighToLow();
    }
}