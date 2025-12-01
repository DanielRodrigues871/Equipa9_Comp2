package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.Candidatura;
import com.upt.lp.componente2.enums.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, String> {
    
    List<Candidatura> findByEstudanteId(String estudanteId);
    
    List<Candidatura> findByOfertaId(String ofertaId);
    
    List<Candidatura> findByCoordenadorResponsavelId(String coordenadorId);
    
    List<Candidatura> findByStatus(StatusCandidatura status);
    
    List<Candidatura> findByEstudanteIdAndOfertaId(String estudanteId, String ofertaId);
    
    long countByOfertaId(String ofertaId);
    
    long countByEstudanteId(String estudanteId);
    
    long countByStatus(StatusCandidatura status);
}
