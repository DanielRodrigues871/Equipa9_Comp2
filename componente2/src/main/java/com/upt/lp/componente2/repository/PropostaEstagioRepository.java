package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, String> {
    
    Optional<PropostaEstagio> findById(String id);
    
    List<PropostaEstagio> findByEmpresaId(String empresaId);
    
    List<PropostaEstagio> findByRepresentanteId(String representanteId);
    
    List<PropostaEstagio> findByStatus(String status);
    
    List<PropostaEstagio> findByTipo(String tipo);
    
    List<PropostaEstagio> findByTituloContainingIgnoreCase(String titulo);
    
    long countByStatus(String status);
    
    boolean existsById(String id);
}
