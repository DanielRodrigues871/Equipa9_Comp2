package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstagioRepository extends JpaRepository<Estagio, UUID> {

    List<Estagio> findByEstudanteId(UUID estudanteId);

    List<Estagio> findByEmpresaId(UUID empresaId);
}



