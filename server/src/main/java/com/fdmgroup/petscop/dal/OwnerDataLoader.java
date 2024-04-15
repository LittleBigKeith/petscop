package com.fdmgroup.petscop.dal;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.model.Owner;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OwnerDataLoader implements ApplicationRunner {
	
	private OwnerRepository ownerRepo;
	
	@Autowired
	public OwnerDataLoader(OwnerRepository ownerRepo) {
		super();
		this.ownerRepo = ownerRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ArrayList<Owner> owners = new ArrayList<>();
		
		Owner tuffnet = new Owner("Tuffnet", "To0thless!&StormflY", Owner.Role.Admin, LocalDate.now().minusDays(365), 8192);
		owners.add(tuffnet);
		Owner ruffnet = new Owner("Ruffnet", "Snotlout123!Fire!", Owner.Role.User, LocalDate.now().minusDays(300), 4096);
		owners.add(ruffnet);
		Owner stoick = new Owner("Stoick", "ToothlessFan23!", Owner.Role.User, LocalDate.now().minusDays(200), 6500);
		owners.add(stoick);
		Owner fishlegs = new Owner("Fishlegs", "FishlegsDragon123>.<", Owner.Role.User, LocalDate.now().minusDays(100), 5048);
		owners.add(fishlegs);
		Owner astrid = new Owner("Astrid", "DragonRider!Astrid", Owner.Role.User, LocalDate.now().minusDays(50), 800);
		owners.add(astrid);
		Owner hiccup = new Owner("Hiccup", "ILoveDragons123$%^", Owner.Role.User, LocalDate.now().minusDays(1), 1600);
		owners.add(hiccup);
		
		ownerRepo.saveAll(owners);
		
		System.out.println();
		System.out.println("<-- Owner Dataloader -->");
		Owner spitelout = new Owner("Spitelout", "S!p1t3l0ut!", Owner.Role.Admin, LocalDate.now(), 100);
		owners.add(spitelout);
		ownerRepo.save(spitelout);
		System.out.println(ownerRepo.findAll());
		
		System.out.println(ownerRepo.findById(3));
		System.out.println(ownerRepo.findByUsernameLikeIgnoreCase("%h%"));
		System.out.println(ownerRepo.findByUsernameLikeIgnoreCase("%R%"));
		
		Owner newOwner = ownerRepo.findById(owners.size()).get();
		newOwner.setUsername("Gobber");
		newOwner.setPassword("St01ckTh3V@st");
		newOwner.setCakeDate(LocalDate.now().minusDays(730));
		newOwner.setGold(16384);
		ownerRepo.save(newOwner);
		System.out.println(ownerRepo.findAll());
		
		ownerRepo.deleteById(owners.size());
		System.out.println(ownerRepo.findAll());
		
		System.out.println(ownerRepo.findAllByOrderByCakeDateAsc());
		System.out.println(ownerRepo.findAllByOrderByCakeDateDesc());
		
		System.out.println(ownerRepo.findAllByOrderByGoldAsc());
		System.out.println(ownerRepo.findAllByOrderByGoldDesc());
		
		System.out.println(ownerRepo.findAllByOrderByUsernameAsc());
		System.out.println(ownerRepo.findAllByOrderByUsernameDesc());
	}
}