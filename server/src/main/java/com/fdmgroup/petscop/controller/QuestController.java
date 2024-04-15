package com.fdmgroup.petscop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.petscop.model.Quest;
import com.fdmgroup.petscop.service.QuestService;

@RestController
@RequestMapping("quest")
@CrossOrigin("http://localhost:5173")
public class QuestController {
	
	private QuestService questService;

	@Autowired
	public QuestController(QuestService questService) {
		super();
		this.questService = questService;
	}
	
	@GetMapping
	public List<Quest> findAll() {
		return questService.findAll();
	}
	
	@GetMapping("find/id/{id}")
	public Quest findById(@PathVariable int id) {
		return questService.findById(id).get();
	}
	
	
	@GetMapping("find/name/{name}")
	public Quest findByName(@PathVariable String name) {
		return questService.findByName(name);
	}
	
	@PostMapping("update")
	public void createOrUpdate(@RequestBody Quest quest) {
		questService.createOrUpdate(quest);
	}
	
	@PutMapping("delete/{id}")
	public void delete(@PathVariable int id) {
		questService.deleteById(id);
	}
}
