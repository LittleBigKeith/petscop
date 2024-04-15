package com.fdmgroup.petscop.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Quest;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Integer>{
	Optional<Quest> findByName(String Name);
	List<Quest> findAll();
	
}
