package com.projeto.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.principal.model.Evento;

public interface EventosRep extends JpaRepository<Evento, Long>{
}
