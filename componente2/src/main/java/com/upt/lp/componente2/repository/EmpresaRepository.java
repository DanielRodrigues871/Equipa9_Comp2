package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    
    Optional<Empresa> findByNif(String nif);
    
    List<Empresa> findByNomeContainingIgnoreCase(String nome);
    
    List<Empresa> findByAtiva(boolean ativa);
    
    boolean existsByNif(String nif);
    
    boolean existsByEmail(String email);
}
