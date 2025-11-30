package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Candidatura;
import com.upt.lp.portalestagios.entity.Coordenador;
import com.upt.lp.portalestagios.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CandidaturaRepository extends JpaRepository<Candidatura, UUID> {

    List<Candidatura> findByEstudante(Estudante estudante);

    @Query("SELECT c FROM Candidatura c WHERE c.coordenadorResponsavel.id = :coordId")
    List<Candidatura> findByCoordenador(UUID coordId);

    @Query("SELECT c FROM Candidatura c WHERE c.oferta.id = :ofertaId")
    List<Candidatura> findByOferta(UUID ofertaId);
}

