package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, String> {
    
    Optional<Departamento> findByCodigo(String codigo);
    
    List<Departamento> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByCodigo(String codigo);
}
