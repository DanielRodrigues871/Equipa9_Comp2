package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, String> {
    
    List<Estagio> findByEstudanteId(String estudanteId);
    
    List<Estagio> findByEmpresaId(String empresaId);
    
    List<Estagio> findByCursoId(String cursoId);
    
    List<Estagio> findByEstadoFinal(String estadoFinal);
    
    List<Estagio> findByDataInicioBetween(java.time.LocalDate start, java.time.LocalDate end);
    
    List<Estagio> findByDataFimBetween(java.time.LocalDate start, java.time.LocalDate end);
}
