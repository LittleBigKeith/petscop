package com.fdmgroup.petscop.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fdmgroup.petscop.model.Owner;

public class AuthUser implements org.springframework.security.core.userdetails.UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Owner owner;

	public AuthUser(Owner owner) {
		super();
		this.owner = owner;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(new SimpleGrantedAuthority(this.owner.getRole().toString()));
		return Arrays.asList(new SimpleGrantedAuthority(this.owner.getRole().toString()));
	}

	@Override
	public String getPassword() {
		return this.owner.getPassword();
	}

	@Override
	public String getUsername() {
		return this.owner.getUsername();		// change to name getter
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
