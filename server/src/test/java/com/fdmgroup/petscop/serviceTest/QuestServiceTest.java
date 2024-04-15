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

import com.fdmgroup.petscop.dal.QuestRepository;
import com.fdmgroup.petscop.model.Quest;
import com.fdmgroup.petscop.service.QuestService;

@ExtendWith(MockitoExtension.class)
public class QuestServiceTest {

    QuestService questService;
    
    @Mock
    QuestRepository questRepoMock;

    @Mock
    Quest questMock;
    
    @BeforeEach
    void init () {
    	questService = new QuestService(questRepoMock);
    	assertNotNull(questRepoMock);
    	assertNotNull(questMock);
    }
   
    @Test
    void TestIf_QuestServiceCanFindById() {
    	int id = 42;
    	when(questRepoMock.findById(id)).thenReturn(Optional.of(questMock));
    	Quest retrievedQuest = questService.findById(id).get();
    	verify(questRepoMock, times(1)).findById(id);
    	assertEquals(retrievedQuest, questMock);
    }
    
    @Test
    void TestIf_QuestServiceCanFindAll() {
    	int numberOfQuests = 42;
    	List<Quest> questList = new ArrayList<>();
    	for (int i = 0; i < numberOfQuests; i++) {
    		questList.add(questMock);
    	}
    	when(questRepoMock.findAll()).thenReturn(questList);
    	List<Quest> retrievedQuests = questService.findAll();
    	verify(questRepoMock, times(1)).findAll();
		assertEquals(retrievedQuests, questList);
    }
    
    @Test
    void TestIf_QuestServiceCanCreate() {
    	questService.createOrUpdate(questMock);
    	verify(questRepoMock, times(1)).save(questMock);
    }

    @Test
    void TestIf_QuestServiceCanDeleteById() {
    	int id = 42;
    	questService.deleteById(id);
    	verify(questRepoMock, times(1)).deleteById(id);
    }
}