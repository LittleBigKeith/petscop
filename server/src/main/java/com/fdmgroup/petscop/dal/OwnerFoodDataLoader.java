package com.fdmgroup.petscop.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Food;
import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerFood;
import com.fdmgroup.petscop.model.OwnerFoodEmbeddedKey;

@Service
@Order(Ordered.LOWEST_PRECEDENCE)
public class OwnerFoodDataLoader implements ApplicationRunner {
	
	private OwnerFoodRepository ownerFoodRepo;
	private OwnerRepository ownerRepo;
	private FoodRepository foodRepo;
	
	@Autowired
	public OwnerFoodDataLoader(OwnerFoodRepository ownerFoodRepo, OwnerRepository ownerRepo, FoodRepository foodRepo) {
		super();
		this.ownerFoodRepo = ownerFoodRepo;
		this.ownerRepo = ownerRepo;
		this.foodRepo = foodRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Owner tuffnet = ownerRepo.findByUsername("Tuffnet").get();
		Owner ruffnet = ownerRepo.findByUsername("Ruffnet").get();
		Owner stoick = ownerRepo.findByUsername("Stoick").get();
		Owner fishlegs = ownerRepo.findByUsername("Fishlegs").get();
		Owner astrid = ownerRepo.findByUsername("Astrid").get();
		Owner hiccup = ownerRepo.findByUsername("Hiccup").get();
		
		Food peanuts = foodRepo.findByName("Peanuts").get();
		Food oatmeal = foodRepo.findByName("Oatmeal").get();
		Food apple = foodRepo.findByName("Apple").get();
		Food berries = foodRepo.findByName("Berries").get();
		Food carrot = foodRepo.findByName("Carrot").get();
		Food pumpkin = foodRepo.findByName("Pumpkin").get();
		
		List<OwnerFood> ownerFoods = new ArrayList<>();
		
		OwnerFood hiccup_peanuts = new OwnerFood(hiccup, peanuts, 10);
		ownerFoods.add(hiccup_peanuts);
		OwnerFood stoick_pumpkin = new OwnerFood(stoick, pumpkin, 5);
		ownerFoods.add(stoick_pumpkin);
		OwnerFood stoick_apple = new OwnerFood(stoick, apple, 2);
		ownerFoods.add(stoick_apple);
		OwnerFood stoick_oatmeal = new OwnerFood(stoick, oatmeal, 1);
		ownerFoods.add(stoick_oatmeal);
		OwnerFood fishlegs_berries = new OwnerFood(fishlegs, berries, 64);
		ownerFoods.add(fishlegs_berries);
		OwnerFood fishlegs_carrot = new OwnerFood(fishlegs, carrot, 23);
		ownerFoods.add(fishlegs_carrot);
		OwnerFood tuffnet_peanuts = new OwnerFood(tuffnet, peanuts, 15);
		ownerFoods.add(tuffnet_peanuts);
		OwnerFood ruffnet_oatmeal = new OwnerFood(ruffnet, oatmeal, 7);
		ownerFoods.add(ruffnet_oatmeal);
		OwnerFood ruffnet_pumpkin = new OwnerFood(ruffnet, pumpkin, 99);
		ownerFoods.add(ruffnet_pumpkin);
		OwnerFood hiccup_carrot = new OwnerFood(hiccup, carrot, 20);
		ownerFoods.add(hiccup_carrot);
		OwnerFood tuffnet_apple = new OwnerFood(tuffnet, apple, 1);
		ownerFoods.add(tuffnet_apple);
		
		ownerFoodRepo.saveAll(ownerFoods);
		
		System.out.println();
		System.out.println("<-- OwnerFood Dataloader -->");
		OwnerFood astrid_berries = new OwnerFood(astrid, berries, 37);
		ownerFoods.add(astrid_berries);
		ownerFoodRepo.save(astrid_berries);
		System.out.println(ownerFoodRepo.findAll());
		
		System.out.println(ownerFoodRepo.findByOwnerFoodEmbeddedKey(new OwnerFoodEmbeddedKey(fishlegs, carrot)).get());
		
		OwnerFood newOwnerFood = ownerFoodRepo.findByOwnerFoodEmbeddedKey(new OwnerFoodEmbeddedKey(astrid, berries)).get();
		newOwnerFood.setQuantity(26);
		ownerFoodRepo.save(newOwnerFood);
		System.out.println(ownerFoodRepo.findAll());
		
		System.out.println(ownerFoodRepo.findByOwnerFoodEmbeddedKeyOwner(hiccup));
		ownerFoodRepo.deleteByOwnerFoodEmbeddedKey(new OwnerFoodEmbeddedKey(astrid, berries));
		System.out.println(ownerFoodRepo.findAll());
		
	}

}
