package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;
import java.util.List;

@Repository
public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, UUID> {

    List<PropostaEstagio> findByEmpresaId(UUID empresaId);

    List<PropostaEstagio> findByStatus(String status);
}
