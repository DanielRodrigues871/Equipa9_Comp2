package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, String> {
    
    Optional<Estudante> findByEmail(String email);
    
    Optional<Estudante> findByNumeroEstudante(String numeroEstudante);
    
    List<Estudante> findByCursoId(String cursoId);
    
    List<Estudante> findByAnoMatricula(int anoMatricula);
    
    boolean existsByEmail(String email);
    
    boolean existsByNumeroEstudante(String numeroEstudante);
}
