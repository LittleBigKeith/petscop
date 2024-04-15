package com.fdmgroup.petscop.dal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Quest;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestDataLoader implements ApplicationRunner {
	
	private QuestRepository questRepo;
	
	@Autowired
	public QuestDataLoader(QuestRepository questRepo) {
		super();
		this.questRepo = questRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ArrayList<Quest> quests = new ArrayList<>();
		
		Quest collectWood = new Quest("Collect Wood", 40, 20, 40);
		quests.add(collectWood);
		Quest collectStone = new Quest("Collect Stone", 100, 60, 100);
		quests.add(collectStone);
		Quest findTreasure = new Quest("Find Treasure", 200, 150, 200);
		quests.add(findTreasure);
		
		questRepo.saveAll(quests);
	}
}