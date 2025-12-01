package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Candidatura;
import com.upt.pt.api.enums.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, String> {

    List<Candidatura> findByEstudanteId(String estudanteId);

    List<Candidatura> findByOfertaId(String ofertaId);

    List<Candidatura> findByStatus(StatusCandidatura status);
}
