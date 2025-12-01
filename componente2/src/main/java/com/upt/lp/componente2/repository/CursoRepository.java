package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String> {
    
    Optional<Curso> findByCodigo(String codigo);
    
    List<Curso> findByNomeContainingIgnoreCase(String nome);
    
    List<Curso> findByDepartamentoId(String departamentoId);
    
    List<Curso> findByCoordenadorId(String coordenadorId);
    
    boolean existsByCodigo(String codigo);
}
