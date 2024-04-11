package com.fdmgroup.petscop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.petscop.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class PetscopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetscopApplication.class, args);
	}
}
