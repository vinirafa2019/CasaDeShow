package com.projeto.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.principal.model.Usuarios;



public interface UserRepository extends JpaRepository<Usuarios, Long> {

	Usuarios findByUsername(String admin);

	@Query("from Usuarios where username=?1")
	public List<Usuarios>buscarUsername(String username);
}
