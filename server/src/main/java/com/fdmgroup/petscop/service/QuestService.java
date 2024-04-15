package com.fdmgroup.petscop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.dal.QuestRepository;
import com.fdmgroup.petscop.model.Quest;

@Service
public class QuestService {
	
	private QuestRepository questRepo;
	
	@Autowired
	public QuestService(QuestRepository questRepo) {
		super();
		this.questRepo = questRepo;
	}
	
	public List<Quest> findAll() {
		return questRepo.findAll();
	}

	public Quest findByName(String defaultName) {
		return questRepo.findByName(defaultName).orElseThrow();
	}
	
	public Optional<Quest> findById(int id) {
		return questRepo.findById(id);
	}
	
	public void createOrUpdate(Quest quest) {
		questRepo.save(quest);
	}
	
	public void deleteById(int id) {
		questRepo.deleteById(id);
	}
	
}
