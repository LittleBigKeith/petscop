package com.fdmgroup.petscop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.petscop.dal.OwnerRepository;
import com.fdmgroup.petscop.model.Owner;

@Service
public class AuthUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	private OwnerRepository ownerRepo;

	@Autowired
	public AuthUserService(OwnerRepository ownerRepo) {
		super();
		this.ownerRepo = ownerRepo;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Owner owner = this.ownerRepo.findByUsername(username).orElseThrow(
				()-> new UsernameNotFoundException(username));
		return new AuthUser(owner);
	}
	
	

}
