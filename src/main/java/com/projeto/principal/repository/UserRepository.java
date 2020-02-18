package com.projeto.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.principal.model.Usuarios;



public interface UserRepository extends JpaRepository<Usuarios, Long> {

	Usuarios findByUsername(String username);
}
