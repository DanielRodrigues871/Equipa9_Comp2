package com.upt.pt.api.repository;

import com.upt.pt.api.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, Long> {

    List<PropostaEstagio> findByEmpresaId(String empresaId);

    List<PropostaEstagio> findByRepresentanteId(String representanteId);

    List<PropostaEstagio> findByStatus(String status);

    List<PropostaEstagio> findByTipo(String tipo);
}
