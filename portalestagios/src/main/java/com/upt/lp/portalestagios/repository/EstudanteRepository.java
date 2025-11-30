package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface EstagioRepository extends JpaRepository<Estagio, UUID> {

    List<Estagio> findByEstudanteId(UUID estudanteId);

    List<Estagio> findByEmpresaId(UUID empresaId);
}



