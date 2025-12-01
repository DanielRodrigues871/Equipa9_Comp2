package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, String>{
	
	Utilizador findByEmail(String email);
	
	boolean existsByEmail(String email);
}
