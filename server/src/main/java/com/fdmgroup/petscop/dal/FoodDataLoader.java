package com.fdmgroup.petscop.dal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Food;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FoodDataLoader implements ApplicationRunner{
	
	private FoodRepository foodRepo;
	
	@Autowired
	public FoodDataLoader(FoodRepository foodRepo) {
		super();
		this.foodRepo = foodRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ArrayList<Food> foods = new ArrayList<>();
		
		Food peanuts = new Food("Peanuts", 50, 20);
		foods.add(peanuts);
		Food oatmeal = new Food("Oatmeal", 120, 50);
		foods.add(oatmeal);
		Food apple = new Food("Apple", 250, 120);
		foods.add(apple);
		Food berries = new Food("Berries", 300, 150);
		foods.add(berries);
		Food carrot = new Food("Carrot", 400, 250);
		foods.add(carrot);
		Food pumpkin = new Food("Pumpkin", 500, 350);
		foods.add(pumpkin);
		
		foodRepo.saveAll(foods);
		
		System.out.println();
		System.out.println("<-- Food Dataloader -->");
		Food salmon = new Food("Salmon", 100, 60);
		foods.add(salmon);
		foodRepo.save(salmon);
		System.out.println(foodRepo.findAll());
		
		System.out.println(foodRepo.findById(3));
		
		Food newFood = foodRepo.findById(foods.size()).get();
		newFood.setName("Strawberries");
		newFood.setPrice(35);
		newFood.setSaturation(20);
		foodRepo.save(newFood);
		System.out.println(foodRepo.findAll());
		
		foodRepo.deleteById(foods.size());
		System.out.println(foodRepo.findAll());
		
		System.out.println(foodRepo.findByName("Carrot"));
		
		System.out.println(foodRepo.findByNameLikeIgnoreCase("%e%"));
		System.out.println(foodRepo.findByNameLikeIgnoreCase("%O%"));
		
		System.out.println(foodRepo.findByPriceLessThanEqualOrderByPriceAsc(12));
		
		System.out.println(foodRepo.findByPriceGreaterThanOrderByPriceAsc(12));
	}
}
