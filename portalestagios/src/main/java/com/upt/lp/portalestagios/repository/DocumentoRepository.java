package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {

    List<Documento> findByEstudanteId(UUID estudanteId);

    List<Documento> findByCandidaturaId(UUID candidaturaId);
}

