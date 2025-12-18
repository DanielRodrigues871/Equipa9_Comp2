package com.upt.pt.api.repository;

import com.upt.pt.api.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, String> { 
    List<PropostaEstagio> findByEmpresaId(String empresaId);

    List<PropostaEstagio> findByRepresentanteId(String representanteId);

    List<PropostaEstagio> findByStatus(String status);

    List<PropostaEstagio> findByTipo(String tipo);
}