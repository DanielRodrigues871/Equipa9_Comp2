package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
    
    List<Documento> findByEstudanteId(String estudanteId);
    
    List<Documento> findByNomeEmpresaContainingIgnoreCase(String nomeEmpresa);
}
