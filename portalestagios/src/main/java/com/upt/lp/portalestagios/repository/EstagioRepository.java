package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, UUID> {

    List<Estagio> findByEstudanteId(UUID estudanteId);
    
    // Buscar estágios por empresa → via proposta → oferta → empresa
    @Query("""
           SELECT e FROM Estagio e 
           WHERE e.proposta.oferta.empresa.id = :empresaId
           """)

    List<Estagio> findByEmpresaId(UUID empresaId);
}



