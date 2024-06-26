package com.fdmgroup.petscop.dal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Pet;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PetDataLoader implements ApplicationRunner {
	
	private PetRepository petRepo;
	
	@Autowired
	public PetDataLoader(PetRepository petRepo) {
		super();
		this.petRepo = petRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ArrayList<Pet> pets = new ArrayList<>();
		
		Pet ash = new Pet("Ash", 1, 40);
		pets.add(ash);
		Pet toothless = new Pet("Toothless", 100, 200);
		pets.add(toothless);
		Pet cloudJumper = new Pet("Cloud Jumper", 200, 280);
		pets.add(cloudJumper);
		Pet meatlug = new Pet("Meatlug", 400, 350);
		pets.add(meatlug);
		Pet terribleTerror = new Pet("Terrible Terror", 800, 420);
		pets.add(terribleTerror);
		Pet thornado = new Pet("Thornado", 1600, 500);
		pets.add(thornado);
		Pet skullCrusher = new Pet("Skull Crusher", 3200, 640);
		pets.add(skullCrusher);
		
		petRepo.saveAll(pets);
		
		System.out.println();
		System.out.println("<-- Pet Dataloader -->");
		Pet pikachiu = new Pet("Pikachiu", 6400, 1234);
		pets.add(pikachiu);
		petRepo.save(pikachiu);
		System.out.println(petRepo.findAll());
		
		System.out.println(petRepo.findById(3));
		
		Pet newPet = petRepo.findById(pets.size()).get();
		newPet.setDefaultName("Bulbasaur");
		newPet.setPrice(5000);
		newPet.setHunger_point(2000);
		petRepo.save(newPet);
		System.out.println(petRepo.findAll());
		
		petRepo.deleteById(pets.size());
		System.out.println(petRepo.findAll());
		
		System.out.println(petRepo.findByDefaultName("Skull Crusher"));
		System.out.println(petRepo.findByDefaultNameLikeIgnoreCase("%T%"));
		System.out.println(petRepo.findByDefaultNameLikeIgnoreCase("%s%"));
		
		System.out.println(petRepo.findByPriceLessThanEqualOrderByPriceAsc(800));
		
		System.out.println(petRepo.findByPriceGreaterThanOrderByPriceAsc(800));
	}
}