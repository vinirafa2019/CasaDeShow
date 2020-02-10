package com.projeto.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.principal.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}
