package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.upt.lp.portalestagios.enums.StatusCandidatura;


import java.util.List;
import java.util.UUID;

public interface CandidaturaRepository extends JpaRepository<Candidatura, UUID> {

	List<Candidatura> findByEstudanteId(UUID estudanteId);


    @Query("SELECT c FROM Candidatura c WHERE c.coordenadorResponsavel.id = :coordId")
    List<Candidatura> findByCoordenadorResponsavelId(UUID coordId);


    @Query("SELECT c FROM Candidatura c WHERE c.oferta.id = :ofertaId")
    List<Candidatura> findByOferta(UUID ofertaId);
    
    long countByStatus(StatusCandidatura status);


}

