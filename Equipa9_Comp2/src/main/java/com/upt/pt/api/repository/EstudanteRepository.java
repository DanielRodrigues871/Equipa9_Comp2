package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Estudante;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, String> {
	
	Optional<Estudante> findByNumeroEstudante(String numeroEstudante);
	
	Optional<Estudante> findByEmail(String email);
	
	List<Estudante> findByCursoId(String cursoId);
	
	List<Estudante> findByAnoMatricula(int anoMatricula);
	
	List<Estudante> findByMediaGreaterThanEqual(double mediaMinima);
	
}
