package com.fdmgroup.petscop.controllerTest;

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

import com.fdmgroup.petscop.controller.QuestController;
import com.fdmgroup.petscop.model.Quest;
import com.fdmgroup.petscop.service.QuestService;

@ExtendWith(MockitoExtension.class)
public class QuestControllerTest {

    QuestController questController;
    
    @Mock
    QuestService questServiceMock;

    @Mock
    Quest questMock;
    
    @BeforeEach
    void init () {
    	questController = new QuestController(questServiceMock);
    	assertNotNull(questServiceMock);
    	assertNotNull(questMock);
    }
   
    @Test
    void TestIf_QuestControllerCanFindById() {
    	int id = 42;
    	when(questServiceMock.findById(id)).thenReturn(Optional.of(questMock));
    	Quest retrievedQuest = questController.findById(id);
    	verify(questServiceMock, times(1)).findById(id);
    	assertEquals(retrievedQuest, questMock);
    }
    
    @Test
    void TestIf_QuestControllerCanFindAll() {
    	int numberOfQuests = 42;
    	List<Quest> petList = new ArrayList<>();
    	for (int i = 0; i < numberOfQuests; i++) {
    		petList.add(questMock);
    	}
    	when(questServiceMock.findAll()).thenReturn(petList);
    	List<Quest> retrievedQuests = questController.findAll();
    	verify(questServiceMock, times(1)).findAll();
		assertEquals(retrievedQuests, petList);
    }
    
    @Test
    void TestIf_QuestControllerCanCreateOrUpdate() {
    	questController.createOrUpdate(questMock);
    	verify(questServiceMock, times(1)).createOrUpdate(questMock);
    }
    
    @Test
    void TestIf_QuestControllerCanDeleteById() {
    	int id = 42;
    	questController.delete(id);
    	verify(questServiceMock, times(1)).deleteById(id);
    }
}
    