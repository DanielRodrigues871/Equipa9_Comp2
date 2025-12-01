package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstagioRepository extends JpaRepository<Estagio, String> {

    List<Estagio> findByEstudanteId(String estudanteId);

    List<Estagio> findByEmpresaId(String empresaId);

    List<Estagio> findByCursoId(String cursoId);

    List<Estagio> findByEstadoFinal(String estadoFinal);
}
