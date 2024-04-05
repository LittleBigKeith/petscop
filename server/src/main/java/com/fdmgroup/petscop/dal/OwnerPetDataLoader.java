package com.fdmgroup.petscop.dal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Owner;
import com.fdmgroup.petscop.model.OwnerPet;
import com.fdmgroup.petscop.model.Pet;

@Service
@Order(Ordered.LOWEST_PRECEDENCE)
public class OwnerPetDataLoader implements ApplicationRunner {
	
	private OwnerPetRepository ownerPetRepo;
	private OwnerRepository ownerRepo;
	private PetRepository petRepo;
	
	@Autowired
	public OwnerPetDataLoader(OwnerPetRepository ownerPetRepo, OwnerRepository ownerRepo, PetRepository petRepo) {
		super();
		this.ownerPetRepo = ownerPetRepo;
		this.ownerRepo = ownerRepo;
		this.petRepo = petRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Owner tuffnet = ownerRepo.findByUsername("Tuffnet").get();
		Owner ruffnet = ownerRepo.findByUsername("Ruffnet").get();
		Owner stoick = ownerRepo.findByUsername("Stoick").get();
		Owner fishlegs = ownerRepo.findByUsername("Fishlegs").get();
		Owner astrid = ownerRepo.findByUsername("Astrid").get();
		Owner hiccup = ownerRepo.findByUsername("Hiccup").get();

		Pet toothless = petRepo.findByDefaultName("Toothless").get();
		Pet cloudJumper = petRepo.findByDefaultName("Cloud Jumper").get();
		Pet meatlug = petRepo.findByDefaultName("Meatlug").get();
		Pet terribleTerror = petRepo.findByDefaultName("Terrible Terror").get();
		Pet thornado = petRepo.findByDefaultName("Thornado").get();
		Pet skullCrusher = petRepo.findByDefaultName("Skull Crusher").get();
		
		List<OwnerPet> ownerPets = new ArrayList<>();
		OwnerPet tuffnet_meatlug_1 = new OwnerPet(tuffnet, meatlug, null, LocalDate.now().minusDays(364), 200);
		ownerPets.add(tuffnet_meatlug_1);
		OwnerPet stoick_cloudJumper = new OwnerPet(stoick, cloudJumper, "Rocky", LocalDate.now().minusDays(299), 140);
		ownerPets.add(stoick_cloudJumper);
		OwnerPet ruffnet_meatlug_2 = new OwnerPet(ruffnet, meatlug, "Chloe", LocalDate.now().minusDays(188), 100);
		ownerPets.add(ruffnet_meatlug_2);
		OwnerPet fishlegs_meatlug = new OwnerPet(fishlegs, meatlug, "Oliver", LocalDate.now().minusDays(180), 350);
		ownerPets.add(fishlegs_meatlug);
		OwnerPet fishlegs_skullCrusher = new OwnerPet(fishlegs, skullCrusher, "Max", LocalDate.now().minusDays(85), 640);
		ownerPets.add(fishlegs_skullCrusher);
		OwnerPet hiccup_thornado_1 = new OwnerPet(hiccup, thornado, "Milo", LocalDate.now().minusDays(10), 480);
		ownerPets.add(hiccup_thornado_1);
		OwnerPet stoick_terribleTerror = new OwnerPet(stoick, terribleTerror, "Charlie", LocalDate.now().minusDays(3), 420);
		ownerPets.add(stoick_terribleTerror);
		OwnerPet tuffnet_skullCrusher = new OwnerPet(tuffnet, skullCrusher, null, LocalDate.now().minusDays(1), 0);
		ownerPets.add(tuffnet_skullCrusher);
		OwnerPet hiccup_thornado_2 = new OwnerPet(hiccup, thornado, "Luna", LocalDate.now(), 300);
		ownerPets.add(hiccup_thornado_2);
		OwnerPet astrid_toothless = new OwnerPet(astrid, toothless, null , LocalDate.now(), 100);
		ownerPets.add(astrid_toothless);
		
		ownerPetRepo.saveAll(ownerPets);
		
		System.out.println();
		System.out.println("<-- OwnerPet Dataloader -->");
		OwnerPet ruffnet_skullCrusher = new OwnerPet(ruffnet, skullCrusher, "enri", LocalDate.now().minusDays(32767), 32767);
		ownerPets.add(ruffnet_skullCrusher);
		ownerPetRepo.save(ruffnet_skullCrusher);
		System.out.println(ownerPetRepo.findAll());
		
		System.out.println(ownerPetRepo.findById(ownerPets.size()));
		System.out.println(ownerPetRepo.findByGivenName("Luna"));
		System.out.println(ownerPetRepo.findByGivenNameLikeIgnoreCase("%r%"));
		System.out.println(ownerPetRepo.findByGivenNameLikeIgnoreCase("%C%"));
		
		OwnerPet newOwnerPet = ownerPetRepo.findById(ownerPets.size()).get();
		newOwnerPet.setOwner(stoick);
		newOwnerPet.setPet(cloudJumper);
		newOwnerPet.setGivenName(null);
		newOwnerPet.setBirthDate(LocalDate.now().minusDays(31));
		newOwnerPet.setHungerPoint(2048);
		ownerPetRepo.save(newOwnerPet);
		System.out.println(ownerPetRepo.findAll());
		
		System.out.println(ownerPetRepo.findByOwner(stoick));
		
		ownerPetRepo.deleteById(ownerPets.size());
		System.out.println(ownerPetRepo.findAll());
		
	}
}