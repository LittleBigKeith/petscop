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

import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.service.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

	OwnerService ownerService;
    
    @Mock
    OwnerRepository ownerRepoMock;

    @Mock
    Owner ownerMock;
    
    @BeforeEach
    void init () {
    	ownerService = new OwnerService(ownerRepoMock, null);
    	assertNotNull(ownerRepoMock);
    	assertNotNull(ownerMock);
    }
   
    @Test
    void TestIf_OwnerServiceCanFindById() {
    	int id = 42;
    	when(ownerRepoMock.findById(id)).thenReturn(Optional.of(ownerMock));
    	Owner retrievedOwner = ownerService.findById(id);
    	verify(ownerRepoMock, times(1)).findById(id);
    	assertEquals(retrievedOwner, ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanFindAll() {
    	int numberOfOwners = 42;
    	List<Owner> ownerList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwners; i++) {
    		ownerList.add(ownerMock);
    	}
    	when(ownerRepoMock.findAll()).thenReturn(ownerList);
    	List<Owner> retrievedOwners = ownerService.findAll();
    	verify(ownerRepoMock, times(1)).findAll();
		assertEquals(retrievedOwners, ownerList);
    }
    
    @Test
    void TestIf_OwnerServiceCanCreate() {
    	doReturn("12345678").when(ownerMock).getPassword();
    	ownerService.create(ownerMock);
    	verify(ownerRepoMock, times(1)).save(ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanFindByRole() {
    	int numberOfOwners = 42;
    	List<Owner> ownerList = new ArrayList<>();
    	for (int i = 0; i < numberOfOwners; i++) {
    		ownerList.add(ownerMock);
    	}
    	Owner.Role role = Owner.Role.Admin;
    	doReturn("12345678").when(ownerMock).getPassword();
    	ownerService.create(ownerMock);
    	when(ownerRepoMock.findByRole(role)).thenReturn(ownerList);
    	List<Owner> retrievedOwners = ownerService.findByRole(role);
    	verify(ownerRepoMock, times(1)).findByRole(role);
    	assertEquals(retrievedOwners, ownerList);
    }
    
    @Test
    void TestIf_OwnerServiceCanUpdate() {
    	ownerService.update(ownerMock);
    	verify(ownerRepoMock, times(1)).save(ownerMock);
    }
    
    @Test
    void TestIf_OwnerServiceCanDeleteById() {
    	int id = 42;
    	ownerService.deleteById(id);
    	verify(ownerRepoMock, times(1)).deleteById(id);
    }
    
    @Test
    void TestIf_OwnerServiceCanFindByUserNameAndPassword() {
    	String userName = "User name";
    	String password = "Password";
    	when(ownerRepoMock.findByUsernameAndPassword(userName, password)).thenReturn(Optional.of(ownerMock));
    	Owner retrievedOwner = ownerService.findByUsername(userName);
    	verify(ownerRepoMock, times(1)).findByUsernameAndPassword(userName, password);
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
    	when(ownerRepoMock.findByUsernameLikeIgnoreCase("%" + searchTerm + "%")).thenReturn(resultList);
    	List<Owner> retrievedOwners = ownerService.searchByUsername(searchTerm);
    	verify(ownerRepoMock, times(1)).findByUsernameLikeIgnoreCase("%" + searchTerm + "%");
    	assertEquals(retrievedOwners, resultList);
    }
    
    @Test
    void testIf_OwnerServiceCanSortByUsernameAtoZ() {
    	ownerService.sortByUsernameAtoZ();
    	verify(ownerRepoMock, times(1)).findAllByOrderByUsernameAsc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByUsernameZtoA() {
    	ownerService.sortByUsernameZtoA();
    	verify(ownerRepoMock, times(1)).findAllByOrderByUsernameDesc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByCakeDateOldToNew() {
    	ownerService.sortByCakeDateOldToNew();
    	verify(ownerRepoMock, times(1)).findAllByOrderByCakeDateAsc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByCakeDateNewToOld() {
    	ownerService.sortByCakeDateNewToOld();
    	verify(ownerRepoMock, times(1)).findAllByOrderByCakeDateDesc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByGoldLowToHigh() {
    	ownerService.sortByGoldLowToHigh();
    	verify(ownerRepoMock, times(1)).findAllByOrderByGoldAsc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByGoldHighToLow() {
    	ownerService.sortByGoldHighToLow();
    	verify(ownerRepoMock, times(1)).findAllByOrderByGoldDesc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByExperienceLowToHigh() {
    	ownerService.sortByExperienceLowToHigh();
    	verify(ownerRepoMock, times(1)).findAllByOrderByExperienceAsc();
    }
    
    @Test
    void testIf_OwnerServiceCanSortByExperienceHighToLow() {
    	ownerService.sortByExperienceHighToLow();
    	verify(ownerRepoMock, times(1)).findAllByOrderByExperienceDesc();
    }
}