package com.fdmgroup.petscop.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.petscop.model.Owner;

@Repository
 public interface OwnerRepository extends JpaRepository<Owner, Integer>{
	 Optional<Owner> findByUsername(String username);
	 Optional<Owner> findByUsernameAndPassword(String username, String password);
	 List<Owner> findByRole(Owner.Role role);
	 List<Owner> findByUsernameLikeIgnoreCase(String searchTerm);
	 List<Owner> findAllByOrderByUsernameAsc();
	 List<Owner> findAllByOrderByUsernameDesc();
	 List<Owner> findAllByOrderByCakeDateAsc();
	 List<Owner> findAllByOrderByCakeDateDesc();
	 List<Owner> findAllByOrderByGoldAsc();
	 List<Owner> findAllByOrderByGoldDesc();
}

